package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.brand.Brand;
import com.dft.wps.model.brand.BrandWrapper;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.item.Item;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsBrands extends WpsSDK {


    public WpsBrands(String accessToken) {
        super(accessToken);
    }

    public List<Brand> getBrands() {
        return getPaginatedBrands(null, "/brands");
    }

    public Brand getBrandById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/brands/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    public List<Brand> getBrandsByIdList(String ids) {
        return getPaginatedBrands(null, "/brands/" + ids);
    }

    public List<Image> getImagesByBrandId(Integer id) {
        return getPaginatedImages(null, "/brands/" + id + "/images");
    }

    public List<Item> getItemsByBrandId(Integer id) {
        return getPaginatedItem(null, "/brands/" + id + "/items");
    }
}
