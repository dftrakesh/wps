package com.dft.wps.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String designationId;
    private String name;
    private String alternateName;
    private String careInstructions;
    private String description;
    private Integer sort;
    private String image360Id;
    private String image360PreviewId;
    private String sizeChartId;
    private String createdAt;
    private String updatedAt;
}
