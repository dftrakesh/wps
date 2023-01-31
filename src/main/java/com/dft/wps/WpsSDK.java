package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.block.BlocksWrapper;
import com.dft.wps.model.brand.BrandsWrapper;
import com.dft.wps.model.country.CountriesWrapper;
import com.dft.wps.model.features.FeaturesWrapper;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.inventory.InventorysWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.ProductsWrapper;
import com.dft.wps.model.taxonomyterm.TaxonomyTermsWrapper;
import com.dft.wps.model.vehicle.VehiclesWrapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class WpsSDK {

    private final String accessToken;
    private final HttpClient client;
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;

    public WpsSDK(String accessToken) {
        this.accessToken = accessToken;
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public AttributesWrapper getPaginatedAttributeValues(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<AttributesWrapper> handler = new JsonBodyHandler<>(AttributesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public TaxonomyTermsWrapper getPaginatedTaxonomyTerms(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<TaxonomyTermsWrapper> handler = new JsonBodyHandler<>(TaxonomyTermsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ItemsWrapper getPaginatedItem(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ItemsWrapper> handler = new JsonBodyHandler<>(ItemsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductsWrapper getPaginatedProducts(String path, HashMap<String, String> params) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        if (params != null) {
            params.forEach(uriBuilder::addParameter);
        }

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ProductsWrapper> handler = new JsonBodyHandler<>(ProductsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductsWrapper getPaginatedProducts(String path) {
        return getPaginatedProducts(path, null);
    }

    @SneakyThrows
    public ImagesWrapper getPaginatedImages(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ImagesWrapper> handler = new JsonBodyHandler<>(ImagesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BlocksWrapper getPaginatedBlocks(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BlocksWrapper> handler = new JsonBodyHandler<>(BlocksWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public VehiclesWrapper getPaginatedVehicles(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<VehiclesWrapper> handler = new JsonBodyHandler<>(VehiclesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandsWrapper getPaginatedBrands(String path, HashMap<String, String> params) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        if (params != null) {
            params.forEach(uriBuilder::addParameter);
        }

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandsWrapper> handler = new JsonBodyHandler<>(BrandsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandsWrapper getPaginatedBrands(String path) {
        return getPaginatedBrands(path, null);
    }

    @SneakyThrows
    public CountriesWrapper getPaginatedCountries(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<CountriesWrapper> handler = new JsonBodyHandler<>(CountriesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public FeaturesWrapper getPaginatedFeatures(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<FeaturesWrapper> handler = new JsonBodyHandler<>(FeaturesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public InventorysWrapper getPaginatedInventory(String path, HashMap<String, String> params) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        if (params != null) {
            params.forEach(uriBuilder::addParameter);
        }

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<InventorysWrapper> handler = new JsonBodyHandler<>(InventorysWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public InventorysWrapper getPaginatedInventory(String path) {
        return getPaginatedInventory(path, null);
    }

    protected URIBuilder baseUrl(URIBuilder uriBuilder, String path) {
        return uriBuilder
            .setScheme("https")
            .setHost("api.wps-inc.com")
            .setPath(path);
    }

    @SneakyThrows
    protected HttpRequest get(URIBuilder uriBuilder) {
        return HttpRequest.newBuilder(uriBuilder.build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
            .GET()
            .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        try {
            return client.sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
                .get()
                .body();
        } catch (Exception exception) {
            Thread.sleep(TIME_OUT_DURATION);
            return getRequestWrapped(request, handler);
        }
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