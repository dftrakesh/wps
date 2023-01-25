package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.attribute.AttributeWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.product.Product;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsAttribute extends WpsSDK {


    public WpsAttribute(String accessToken) {
        super(accessToken);
    }

    public List<Attribute> getAttributeKeys() {
        return getPaginatedAttributeValues(null, "/attributekeys");
    }

    public Attribute getAttributeKeyById(Integer id) {
        return getAttributeById(null, "/attributekeys/" + id);
    }

    public List<Attribute> getAttributeKeysByIdList(Integer ids) {
        return getPaginatedAttributeValues(null, "/attributekeys/" + ids);
    }

    public List<Attribute> getAttributeValues() {
        return getPaginatedAttributeValues(null, "/attributevalues");
    }

    public Attribute getAttributeValuesById(Integer id) {
        return getAttributeById(null, "/attributevalues/" + id);
    }

    public List<Attribute> getAttributeValuesByIdList(Integer ids) {
        return getPaginatedAttributeValues(null, "/attributevalues/" + ids);
    }

    public Attribute getParentAttributeKey(Integer id) {
        return getAttributeById(null, "/attributevalues/" + id + "/attributekey");
    }

    public List<Product> getProductsByAttributeKey(Integer id) {
        return getPaginatedProducts(null, "/attributevalues/" + id + "/products", null);

    }

    public List<Item> getItemsByAttributeKey(Integer id) {
        return getPaginatedItem(null, "/attributevalues/" + id + "/items");
    }

    public Attribute getAttributeById(Integer id, String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<AttributeWrapper> handler = new JsonBodyHandler<>(AttributeWrapper.class);
        AttributeWrapper attributeWrapper = getRequestWrapped(request, handler);
        return attributeWrapper.getData();
    }
}
