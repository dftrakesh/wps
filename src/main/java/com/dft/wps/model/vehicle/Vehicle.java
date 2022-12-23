package com.dft.wps.model.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

    private Integer id;
    private Integer vehiclemodelId;
    private Integer vehicleyearId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
