package com.dft.wps.model.inventory;

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
public class Inventory {

    private Integer id;
    private Integer itemId;
    private String sku;
    private Integer caWarehouse;
    private Integer gaWarehouse;
    private Integer idWarehouse;
    private Integer inWarehouse;
    private Integer paWarehouse;
    private Integer txWarehouse;
    private Integer total;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime updatedAt;
}