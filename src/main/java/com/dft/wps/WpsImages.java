package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.block.BlocksWrapper;
import com.dft.wps.model.brand.BrandsWrapper;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.image.ImageWrapper;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.ProductsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WpsImages extends WpsSDK {


    public WpsImages(String accessToken) {
        super(accessToken);
    }

    public ImagesWrapper getImages() {
        return getPaginatedImages("/images");
    }

    public Image getImageById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/images/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ImageWrapper> handler = new JsonBodyHandler<>(ImageWrapper.class);
        ImageWrapper imageWrapper = getRequestWrapped(request, handler);
        return imageWrapper.getData();
    }

    public ImagesWrapper getImageByIdList(String ids) {
        return getPaginatedImages("/images/" + ids);
    }

    public BlocksWrapper getBlocksByImage(Integer id) {
        return getPaginatedBlocks("/images/" + id + "/blocks");
    }

    public ItemsWrapper getItemsByImage(Integer id) {
        return getPaginatedItem("/images/" + id + "items");
    }

    public ProductsWrapper getProductsByImage(Integer id) {
        return getPaginatedProducts("/images/" + id + "/products");
    }

    public BrandsWrapper getBrandsByImage(Integer id) {
        return getPaginatedBrands("/images/" + id + "/brands");
    }

}