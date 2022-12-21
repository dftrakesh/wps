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
public class Order {

    private String poNumber;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
