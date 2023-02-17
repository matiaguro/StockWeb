package com.stockweb.demo.config.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    RESOURCE_NOT_FOUND("No se encontraron datos"),
    RESOURCE_ALREADY_EXISTS("Este recurso ya existe"),

    EXPECTED_FAILURE("Error esperado");

    private final String defaultMessage;

}