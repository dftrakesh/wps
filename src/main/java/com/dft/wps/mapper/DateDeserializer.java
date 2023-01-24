package com.dft.wps.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
            return LocalDateTime.parse(jsonParser.getText().trim(), ISO_FORMATTER);
        } catch (Exception exception) {
            log.error("Exception occurred while parsing date: {}", exception.getMessage(), exception);
        }
        return null;
    }
}