package com.dft.wps.model.product;

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
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime updatedAt;
    private ProductImagesWrapper images;
    private ProductItems items;
    private ProductTaxonomyTermsWrapper taxonomyterms;
}
