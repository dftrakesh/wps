package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.item.ItemWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.ProductsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WpsItems extends WpsSDK {

    public WpsItems(String accessToken) {
        super(accessToken);
    }

    public ItemsWrapper getItems() {
        return getPaginatedItem("/items");
    }

    public Item getItemById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/items/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ItemWrapper> handler = new JsonBodyHandler<>(ItemWrapper.class);
        ItemWrapper itemWrapper = getRequestWrapped(request, handler);
        return itemWrapper.getData();
    }

    public ItemsWrapper getItemsByIdList(String ids) {
        return getPaginatedItem("/items/" + ids);
    }

    public AttributesWrapper getAttributesByItemId(Integer id) {
        return getPaginatedAttributeValues("/items/" + id + "/attributevalues");
    }

    public ImagesWrapper getImagesByItemId(Integer id) {
        return getPaginatedImages("/items/" + id + "/images");
    }

    public ProductsWrapper getProductsByItemId(Integer id) {
        return getPaginatedProducts("/items/" + id + "/products");
    }
}