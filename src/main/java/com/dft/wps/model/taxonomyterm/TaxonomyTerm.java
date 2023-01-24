package com.dft.wps.model.taxonomyterm;

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
public class TaxonomyTerm {

    private Integer id;
    private Integer vocabularyId;
    private Integer parentId;
    private String name;
    private String slug;
    private String description;
    private String link;
    private Boolean linkTargetBlank;
    private Integer left;
    private Integer right;
    private Integer depth;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime updatedAt;
}