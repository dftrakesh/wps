package com.dft.wps.model.item;

import com.dft.wps.mapper.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

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
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime updatedAt;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime publishedAt;
    private ItemAttributesWrapper attributevalues;
    private ItemImagesWrapper images;
    private ItemInventorysWrapper inventory;
}
