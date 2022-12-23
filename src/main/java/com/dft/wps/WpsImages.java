package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.block.Block;
import com.dft.wps.model.brand.Brand;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.image.ImageWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.product.Product;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsImages extends WpsSDK {


    public WpsImages(String accessToken) {
        super(accessToken);
    }

    public List<Image> getImages() {
        return getPaginatedImages(null, "/images");
    }

    public Image getImageById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/images/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ImageWrapper> handler = new JsonBodyHandler<>(ImageWrapper.class);
        ImageWrapper imageWrapper = getRequestWrapped(request, handler);
        return imageWrapper.getData();
    }

    public List<Image> getImageByIdList(String ids) {
        return getPaginatedImages(null, "/images/" + ids);
    }

    public List<Block> getBlocksByImage(Integer id) {
        return getPaginatedBlocks(null, "/images/" + id + "/blocks");
    }

    public List<Item> getItemsByImage(Integer id) {
        return getPaginatedItem(null, "/images/" + id + "items");
    }

    public List<Product> getProductsByImage(Integer id) {
        return getPaginatedProducts(null, "/images/" + id + "/products");
    }

    public List<Brand> getBrandsByImage(Integer id) {
        return getPaginatedBrands(null, "/images/" + id + "/brands");
    }

}
