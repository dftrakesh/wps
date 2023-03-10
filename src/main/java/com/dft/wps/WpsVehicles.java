package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.vehicle.Vehicle;
import com.dft.wps.model.vehicle.VehicleWrapper;
import com.dft.wps.model.vehicle.VehiclesWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WpsVehicles extends WpsSDK {


    public WpsVehicles(String accessToken) {
        super(accessToken);
    }

    public VehiclesWrapper getVehicles() {
        return getPaginatedVehicles("/vehicles");
    }

    public Vehicle getVehicleById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/vehicles/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<VehicleWrapper> handler = new JsonBodyHandler<>(VehicleWrapper.class);
        VehicleWrapper vehicleWrapper = getRequestWrapped(request, handler);
        return vehicleWrapper.getData();
    }

    public VehiclesWrapper getVehiclesByIdList(String ids) {
        return getPaginatedVehicles("/vehicles/" + ids);
    }

    public AttributesWrapper getAttributeValuesByVehicleId(Integer id) {
        return getPaginatedAttributeValues("/vehicles/" + id + "/attributevalues");
    }

    public ItemsWrapper getItemsByVehicleId(Integer id) {
        return getPaginatedItem("/products/" + id + "/items");
    }
}