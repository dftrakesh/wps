package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.block.Block;
import com.dft.wps.model.block.BlockWrapper;
import com.dft.wps.model.image.Image;
import com.dft.wps.model.product.Product;
import com.dft.wps.model.product.ProductsWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsBlocks extends WpsSDK {


    public WpsBlocks(String accessToken) {
        super(accessToken);
    }

    public List<Block> getBlocks() {
        return getPaginatedBlocks("/blocks");
    }

    public Block getBlockById(String id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/blocks/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BlockWrapper> handler = new JsonBodyHandler<>(BlockWrapper.class);
        BlockWrapper blockWrapper = getRequestWrapped(request, handler);
        return blockWrapper.getData();
    }

    public List<Block> getBlockByIdList(String ids) {
        return getPaginatedBlocks("/blocks/" + ids);
    }

    public List<Image> getImagesByBlockId(String id) {
        return getPaginatedImages("/blocks/" + id + "/images");
    }

    public ProductsWrapper getProductsByBlockId(String id) {
        return getPaginatedProducts( "/blocks/" + id + "/products");
    }

}
