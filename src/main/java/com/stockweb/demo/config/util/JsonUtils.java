package com.stockweb.demo.config.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;

public abstract class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public static String objectToJson(Object arg) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(arg);
    }

    public static <T> T jsonToObject(String json, Class<T> arg) throws IOException {
        return OBJECT_MAPPER.readValue(json, arg);
    }
}
