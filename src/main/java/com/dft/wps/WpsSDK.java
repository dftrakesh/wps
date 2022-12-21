package com.dft.wps;

import com.dft.wps.exception.NotFoundException;
import com.dft.wps.exception.UnProcessableEntityException;
import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.attribute.AttributeWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.item.ItemWrapper;
import com.dft.wps.model.order.Order;
import com.dft.wps.model.order.OrderWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
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

    public List<Item> getItems() {
        return getPaginatedItem(null);
    }

    @SneakyThrows
    public List<Item> getPaginatedItem(String cursor) {
        List<Item> itemList = new ArrayList<>();
        do {
            URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/items")
                .addParameter("page[size]", "10000")
                .addParameter("page[cursor]", cursor);

            HttpRequest request = get(uriBuilder);
            HttpResponse.BodyHandler<ItemWrapper> handler = new JsonBodyHandler<>(ItemWrapper.class);
            ItemWrapper itemWrapper = getRequestWrapped(request, handler);
            itemList.addAll(itemWrapper.getData());
            cursor = itemWrapper.getMeta().getCursor().getNext();
        } while (cursor != null);
        return itemList;
    }

    @SneakyThrows
    public List<Order> getOrders(String fromDate, String toDate) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/orders")
            .addParameter("from_date", fromDate)
            .addParameter("to_date", toDate);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        OrderWrapper orderWrapper = getRequestWrapped(request, handler);
        return orderWrapper.getData();
    }

    public List<Attribute> getAttributeKeys() {
        return getPaginatedAttributeValues(null, "attributekeys");
    }

    public List<Attribute> getAttributeValues() {
        return getPaginatedAttributeValues(null, "attributevalues");
    }

    @SneakyThrows
    public List<Attribute> getPaginatedAttributeValues(String cursor, String path) {
        List<Attribute> attributeList = new ArrayList<>();
        do {
            URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/" + path)
                .addParameter("page[size]", "500")
                .addParameter("page[cursor]", cursor);

            HttpRequest request = get(uriBuilder);
            HttpResponse.BodyHandler<AttributeWrapper> handler = new JsonBodyHandler<>(AttributeWrapper.class);
            AttributeWrapper attributeWrapper = getRequestWrapped(request, handler);
            attributeList.addAll(attributeWrapper.getData());
            cursor = attributeWrapper.getMeta().getCursor().getNext();
        } while (cursor != null);
        return attributeList;
    }

    private URIBuilder baseUrl(URIBuilder uriBuilder, String path) {
        return uriBuilder
            .setScheme("https")
            .setHost("api.wps-inc.com")
            .setPath(path);
    }

    @SneakyThrows
    private HttpRequest get(URIBuilder uriBuilder) {
        return HttpRequest.newBuilder(uriBuilder.build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
            .GET()
            .build();
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
