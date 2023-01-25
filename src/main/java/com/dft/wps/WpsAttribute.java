package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.attribute.AttributeWrapper;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.Product;
import com.dft.wps.model.product.ProductsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsAttribute extends WpsSDK {


    public WpsAttribute(String accessToken) {
        super(accessToken);
    }

    public AttributesWrapper getAttributeKeys() {
        return getPaginatedAttributeValues("/attributekeys");
    }

    public Attribute getAttributeKeyById(Integer id) {
        return getAttributeById("/attributekeys/" + id);
    }

    public AttributesWrapper getAttributeKeysByIdList(Integer ids) {
        return getPaginatedAttributeValues("/attributekeys/" + ids);
    }

    public AttributesWrapper getAttributeValues() {
        return getPaginatedAttributeValues("/attributevalues");
    }

    public Attribute getAttributeValuesById(Integer id) {
        return getAttributeById("/attributevalues/" + id);
    }

    public AttributesWrapper getAttributeValuesByIdList(Integer ids) {
        return getPaginatedAttributeValues("/attributevalues/" + ids);
    }

    public Attribute getParentAttributeKey(Integer id) {
        return getAttributeById("/attributevalues/" + id + "/attributekey");
    }

    public ProductsWrapper getProductsByAttributeKey(Integer id) {
        return getPaginatedProducts("/attributevalues/" + id + "/products");

    }

    public ItemsWrapper getItemsByAttributeKey(Integer id) {
        return getPaginatedItem("/attributevalues/" + id + "/items");
    }

    public Attribute getAttributeById(String path) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), path);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<AttributeWrapper> handler = new JsonBodyHandler<>(AttributeWrapper.class);
        AttributeWrapper attributeWrapper = getRequestWrapped(request, handler);
        return attributeWrapper.getData();
    }
}
