package com.dft.wps;

import com.dft.wps.exception.NotFoundException;
import com.dft.wps.exception.UnProcessableEntityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WpsSDK {

    private final String accessToken;
    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;

    public WpsSDK(String accessToken) {
        this.accessToken = accessToken;
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> handleUnknownError(client, request, handler, response))
            .get();
    }

    @SneakyThrows
    public <T> CompletableFuture<T> handleUnknownError(HttpClient client,
                                                       HttpRequest request,
                                                       HttpResponse.BodyHandler<T> handler,
                                                       HttpResponse<T> resp) {
        if (resp.statusCode() == HttpStatus.SC_OK) {
            return CompletableFuture.completedFuture(resp.body());
        }
        if (resp.statusCode() == HttpStatus.SC_NO_CONTENT) {
            return CompletableFuture.completedFuture(resp.body());
        }
        if (resp.statusCode() == HttpStatus.SC_NOT_FOUND) {
            throw new NotFoundException(objectMapper.writeValueAsString(resp.body()));
        }
        if (resp.statusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
            throw new UnProcessableEntityException(objectMapper.writeValueAsString(resp.body()));
        }
        if (resp.statusCode() == 429) {
            return CompletableFuture.completedFuture(tryResend(client, request, handler, resp, 1).get().body());
        }

        throw new Exception();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}
