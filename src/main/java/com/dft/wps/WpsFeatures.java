package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.attribute.AttributesWrapper;
import com.dft.wps.model.features.Feature;
import com.dft.wps.model.features.FeatureWrapper;
import com.dft.wps.model.features.FeaturesWrapper;
import com.dft.wps.model.item.ItemsWrapper;
import com.dft.wps.model.vehicle.VehiclesWrapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WpsFeatures extends WpsSDK {


    public WpsFeatures(String accessToken) {
        super(accessToken);
    }

    public FeaturesWrapper getFeatures() {
        return getPaginatedFeatures("/features");
    }

    public Feature getFeatureById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/features/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<FeatureWrapper> handler = new JsonBodyHandler<>(FeatureWrapper.class);
        FeatureWrapper featureWrapper = getRequestWrapped(request, handler);
        return featureWrapper.getData();
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