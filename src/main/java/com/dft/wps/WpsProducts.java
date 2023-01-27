package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.block.BlocksWrapper;
import com.dft.wps.model.image.ImagesWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.product.Product;
import com.dft.wps.model.product.ProductWrapper;
import com.dft.wps.model.product.ProductsWrapper;
import com.dft.wps.model.taxonomyterm.TaxonomyTermsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class WpsProducts extends WpsSDK {


    public WpsProducts(String accessToken) {
        super(accessToken);
    }

    public ProductsWrapper getProducts() {
        return getPaginatedProducts( "/products");
    }

    public ProductsWrapper getProducts(HashMap<String, String> params) {
        return getPaginatedProducts("/products", params);
    }

    public Product getProductById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/products/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        ProductWrapper productWrapper = getRequestWrapped(request, handler);
        return productWrapper.getData();
    }

    public ProductsWrapper getProductsByIdList(String ids) {
        return getPaginatedProducts("/products/" + ids);
    }

    public AttributesWrapper getAttributeKeysByProductId(Integer id) {
        return getPaginatedAttributeValues( "/products/" + id + "/attributekeys");
    }

    public AttributesWrapper getAttributeValuesByProductId(Integer id) {
        return getPaginatedAttributeValues("/products/" + id + "/attributevalues");
    }

    public BlocksWrapper getBlocksByProductId(Integer id) {
        return getPaginatedBlocks("/products/" + id + "/blocks");
    }

    public ImagesWrapper getImagesByProductId(Integer id) {
        return getPaginatedImages("/products/" + id + "/images");
    }

    public ItemsWrapper getItemsByProductId(Integer id) {
        return getPaginatedItem("/products/" + id + "/items");
    }

    public TaxonomyTermsWrapper getTaxonomyTermsByProductId(Integer id) {
        return getPaginatedTaxonomyTerms("/products/" + id + "/taxonomyterms");
    }
}