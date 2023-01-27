package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.inventory.Inventory;
import com.dft.wps.model.inventory.InventoryWrapper;
import com.dft.wps.model.inventory.InventorysWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class WpsInventory extends WpsSDK {

    public WpsInventory(String accessToken) {
        super(accessToken);
    }

    public InventorysWrapper getInventories() {
        return getPaginatedInventory("/inventory");
    }

    public InventorysWrapper getInventories(HashMap<String, String> params) {
        return getPaginatedInventory("/inventory", params);
    }

    public Inventory getInventoryById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/inventory/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<InventoryWrapper> handler = new JsonBodyHandler<>(InventoryWrapper.class);
        InventoryWrapper inventoryWrapper = getRequestWrapped(request, handler);
        return inventoryWrapper.getData();
    }

    public InventorysWrapper getInventoriesByIdList(String ids) {
        return getPaginatedInventory("/inventory/" + ids);
    }
}