package com.dft.wps.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail {

    private String orderNumber;
    private String orderStatus;
    private String invoiceNumber;
    private Integer freight;
    private Integer miscCharges;
    private Double orderTotal;
    private String warehouse;
    private String orderDate;
    private String shipDate;
    private String invoiceDate;
    private String shipVia;
    private List<String> trackingNumbers = new ArrayList<>();
    private List<OrderItem> items = new ArrayList<>();
}
