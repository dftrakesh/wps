package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.country.Country;
import com.dft.wps.model.country.CountryWrapper;
import com.dft.wps.model.item.Item;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsCountries extends WpsSDK {

    public WpsCountries(String accessToken) {
        super(accessToken);
    }

    public List<Country> getCountries() {
        return getPaginatedCountries(null, "/countries");
    }

    public Country getCountryById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/countries/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<CountryWrapper> handler = new JsonBodyHandler<>(CountryWrapper.class);
        CountryWrapper countryWrapper = getRequestWrapped(request, handler);
        return countryWrapper.getData();
    }

    public List<Country> getCountriesByIdList(String ids) {
        return getPaginatedCountries(null, "/countries/" + ids);
    }

    public List<Item> getItemsByCountryId(Integer id) {
        return getPaginatedItem(null, "/countries/" + id + "/items");
    }
}
