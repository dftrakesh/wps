package com.dft.wps;

import com.dft.wps.handler.JsonBodyHandler;
import com.dft.wps.model.order.Order;
import com.dft.wps.model.order.OrderWrapper;
import com.dft.wps.model.order.OrdersWrapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WpsOrders extends WpsSDK {


    public WpsOrders(String accessToken) {
        super(accessToken);
    }

    @SneakyThrows
    public List<Order> getOrders(String fromDate, String toDate) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/orders")
            .addParameter("from_date", fromDate)
            .addParameter("to_date", toDate);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrdersWrapper> handler = new JsonBodyHandler<>(OrdersWrapper.class);
        OrdersWrapper ordersWrapper = getRequestWrapped(request, handler);
        return ordersWrapper.getData();
    }

    @SneakyThrows
    public Order getOrderByPoNumber(String poNumber) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/orders/" + poNumber);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        OrderWrapper orderWrapper = getRequestWrapped(request, handler);
        return orderWrapper.getData();
    }
}
