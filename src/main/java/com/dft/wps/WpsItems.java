package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.item.ItemWrapper;
import com.dft.wps.model.product.Product;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsItems extends WpsSDK {

    public WpsItems(String accessToken) {
        super(accessToken);
    }

    public List<Item> getItems() {
        return getPaginatedItem(null, "/items");
    }

    public Item getItemById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/items/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ItemWrapper> handler = new JsonBodyHandler<>(ItemWrapper.class);
        ItemWrapper itemWrapper = getRequestWrapped(request, handler);
        return itemWrapper.getData();
    }

    public List<Item> getItemsByIdList(String ids) {
        return getPaginatedItem(null, "/items/" + ids);
    }

    public List<Attribute> getAttributesByItemId(Integer id) {
        return getPaginatedAttributeValues(null, "/items/" + id + "/attributevalues");
    }

    public List<Image> getImagesByItemId(Integer id) {
        return getPaginatedImages(null, "/items/" + id + "/images");
    }

    public List<Product> getProductsByItemId(Integer id) {
        return getPaginatedProducts(null, "/items/" + id + "/products", null);
    }
}
