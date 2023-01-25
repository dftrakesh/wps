package com.dft.wps;

import com.dft.wps.exception.NotFoundException;
import com.dft.wps.exception.UnProcessableEntityException;
import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.block.Block;
import com.dft.wps.model.block.BlocksWrapper;
import com.dft.wps.model.brand.Brand;
import com.dft.wps.model.brand.BrandsWrapper;
import com.dft.wps.model.country.CountriesWrapper;
import com.dft.wps.model.country.Country;
import com.dft.wps.model.features.Feature;
import com.dft.wps.model.features.FeaturesWrapper;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.inventory.Inventory;
import com.dft.wps.model.inventory.InventorysWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.ProductsWrapper;
import com.dft.wps.model.taxonomyterm.TaxonomyTerm;
import com.dft.wps.model.taxonomyterm.TaxonomyTermsWrapper;
import com.dft.wps.model.vehicle.Vehicle;
import com.dft.wps.model.vehicle.VehiclesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Image> getPaginatedImages(String path) {
        List<Image> imageList = new ArrayList<>();

        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ImagesWrapper> handler = new JsonBodyHandler<>(ImagesWrapper.class);
        ImagesWrapper imagesWrapper = getRequestWrapped(request, handler);
        imageList.addAll(imagesWrapper.getData());
        return imageList;
    }

    @SneakyThrows
    public List<Block> getPaginatedBlocks(String path) {
        List<Block> blockList = new ArrayList<>();

        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BlocksWrapper> handler = new JsonBodyHandler<>(BlocksWrapper.class);
        BlocksWrapper blocksWrapper = getRequestWrapped(request, handler);
        blockList.addAll(blocksWrapper.getData());
        return blockList;
    }

    @SneakyThrows
    public List<Vehicle> getPaginatedVehicles(String path) {
        List<Vehicle> vehicleList = new ArrayList<>();
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<VehiclesWrapper> handler = new JsonBodyHandler<>(VehiclesWrapper.class);
        VehiclesWrapper vehiclesWrapper = getRequestWrapped(request, handler);
        vehicleList.addAll(vehiclesWrapper.getData());
        return vehicleList;
    }

    @SneakyThrows
    public List<Brand> getPaginatedBrands(String path) {
        List<Brand> brandList = new ArrayList<>();
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandsWrapper> handler = new JsonBodyHandler<>(BrandsWrapper.class);
        BrandsWrapper brandsWrapper = getRequestWrapped(request, handler);
        brandList.addAll(brandsWrapper.getData());
        return brandList;
    }

    @SneakyThrows
    public List<Country> getPaginatedCountries(String path) {
        List<Country> countryList = new ArrayList<>();

        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<CountriesWrapper> handler = new JsonBodyHandler<>(CountriesWrapper.class);
        CountriesWrapper countriesWrapper = getRequestWrapped(request, handler);
        countryList.addAll(countriesWrapper.getData());
        return countryList;
    }

    @SneakyThrows
    public List<Feature> getPaginatedFeatures(String path) {
        List<Feature> featureList = new ArrayList<>();

        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<FeaturesWrapper> handler = new JsonBodyHandler<>(FeaturesWrapper.class);
        FeaturesWrapper featuresWrapper = getRequestWrapped(request, handler);
        featureList.addAll(featuresWrapper.getData());
        return featureList;
    }

    @SneakyThrows
    public List<Inventory> getPaginatedInventory(String path) {
        List<Inventory> inventoryList = new ArrayList<>();

        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
            .addParameter("page[size]", "250");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<InventorysWrapper> handler = new JsonBodyHandler<>(InventorysWrapper.class);
        InventorysWrapper inventorysWrapper = getRequestWrapped(request, handler);
        inventoryList.addAll(inventorysWrapper.getData());
        return inventoryList;
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
