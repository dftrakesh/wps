package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.Attribute;
import com.dft.wps.model.item.Item;
import com.dft.wps.model.vehicle.Vehicle;
import com.dft.wps.model.vehicle.VehicleWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsVehicles extends WpsSDK {


    public WpsVehicles(String accessToken) {
        super(accessToken);
    }

    public List<Vehicle> getVehicles() {
        return getPaginatedVehicles(null, "/vehicles");
    }

    public Vehicle getVehicleById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/vehicles/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<VehicleWrapper> handler = new JsonBodyHandler<>(VehicleWrapper.class);
        VehicleWrapper vehicleWrapper = getRequestWrapped(request, handler);
        return vehicleWrapper.getData();
    }

    public List<Vehicle> getVehiclesByIdList(String ids) {
        return getPaginatedVehicles(null, "/vehicles/" + ids);
    }

    public List<Attribute> getAttributeValuesByVehicleId(Integer id) {
        return getPaginatedAttributeValues(null, "/vehicles/" + id + "/attributevalues");
    }

    public List<Item> getItemsByVehicleId(Integer id) {
        return getPaginatedItem(null, "/products/" + id + "/items");
    }
}
