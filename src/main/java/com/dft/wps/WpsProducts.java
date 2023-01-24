package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.block.Block;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.product.Product;
import com.dft.wps.model.product.ProductWrapper;
import com.dft.wps.model.taxonomyterm.TaxonomyTerm;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsProducts extends WpsSDK {


    public WpsProducts(String accessToken) {
        super(accessToken);
    }

    public List<Product> getProducts() {
        return getPaginatedProducts(null, "/products");
    }

    public Product getProductById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/products/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        ProductWrapper productWrapper = getRequestWrapped(request, handler);
        return productWrapper.getData();
    }

    public List<Product> getProductsByIdList(String ids) {
        return getPaginatedProducts(null, "/products/" + ids);
    }

    public List<Attribute> getAttributeKeysByProductId(Integer id) {
        return getPaginatedAttributeValues(null, "/products/" + id + "/attributekeys");
    }

    public List<Attribute> getAttributeValuesByProductId(Integer id) {
        return getPaginatedAttributeValues(null, "/products/" + id + "/attributevalues");
    }

    public List<Block> getBlocksByProductId(Integer id) {
        return getPaginatedBlocks(null, "/products/" + id + "/blocks");
    }

    public List<Image> getImagesByProductId(Integer id) {
        return getPaginatedImages(null, "/products/" + id + "/images");
    }

    public List<Item> getItemsByProductId(Integer id) {
        return getPaginatedItem(null, "/products/" + id + "/items");
    }

    public List<TaxonomyTerm> getTaxonomyTermsByProductId(Integer id) {
        return getPaginatedTaxonomyTerms(null, "/products/" + id + "/taxonomyterms");
    }
}