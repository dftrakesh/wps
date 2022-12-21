package com.dft.wps.model.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private Integer id;
    private Integer brandId;
    private Integer countryId;
    private Integer productId;
    private String sku;
    private String name;
    private String listPrice;
    private String standardDealerPrice;
    private String supplierProductId;
    private Double length;
    private Double width;
    private Double height;
    private Double weight;
    private String upc;
    private String supersededSku;
    private String statusId;
    private String status;
    private String unitOfMeasurementId;
    private Boolean hasMapPolicy;
    private Integer sort;
    private String createdAt;
    private String updatedAt;
    private String publishedAt;
}
