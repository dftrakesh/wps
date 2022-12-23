package com.dft.wps.model.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    private Integer id;
    private String domain;
    private String path;
    private String filename;
    private String alt;
    private String mime;
    private Integer width;
    private Integer height;
    private Integer size;
    private String signature;
    private String createdAt;
    private String updatedAt;
}
