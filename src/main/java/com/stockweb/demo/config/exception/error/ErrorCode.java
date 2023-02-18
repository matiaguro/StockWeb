package com.stockweb.demo.config.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    RESOURCE_NOT_FOUND("No se encontraron datos"),
    RESOURCE_ALREADY_EXISTS("Este recurso ya existe"),
    EXPECTED_FAILURE("Error esperado"),
    ROLE_INVALID("El usuario no tiene permisos para realizar esta accion"),
    BAD_CREDENTIALS ("Las credenciales enviadas no son validas");

    private final String defaultMessage;

}