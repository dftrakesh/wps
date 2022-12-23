package com.dft.wps.model.features;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

    private Integer id;
    private Integer productId;
    private String iconId;
    private Integer sort;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
