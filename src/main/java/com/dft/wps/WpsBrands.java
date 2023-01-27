package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.brand.Brand;
import com.dft.wps.model.brand.BrandWrapper;
import com.dft.wps.model.brand.BrandsWrapper;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class WpsBrands extends WpsSDK {


    public WpsBrands(String accessToken) {
        super(accessToken);
    }

    public BrandsWrapper getBrands() {
        return getPaginatedBrands("/brands");
    }

    public BrandsWrapper getBrands(HashMap<String, String> params) {
        return getPaginatedBrands("/brands", params);
    }

    public Brand getBrandById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/brands/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    public BrandsWrapper getBrandsByIdList(String ids) {
        return getPaginatedBrands("/brands/" + ids);
    }

    public ImagesWrapper getImagesByBrandId(Integer id) {
        return getPaginatedImages("/brands/" + id + "/images");
    }

    public ItemsWrapper getItemsByBrandId(Integer id) {
        return getPaginatedItem("/brands/" + id + "/items");
    }
}