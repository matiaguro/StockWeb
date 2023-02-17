package com.stockweb.demo.config.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {

    /**
     * El código de error de nivel de aplicación único y detallado.
     */
    @NotNull ErrorCode code;

    /**
     * La descripción legible por humanos para un problema. Proporcione un mensaje de error más granular no estándar.
     */
    @NotNull String detail;

    /**
     * El campo que causó el error. Si el campo está en el cuerpo, establezca este valor en el puntero JSON de ese campo.
     * Ejemplo: "campo": {"$ref": "#/body-field"}
     */
    String field;

    /**
     * El valor del campo que provocó el error.
     */
    Object value;

    /**
     * The location of the field that caused the error. Value is `BODY`, `PATH`, `QUERY` or `HEADER`.
     */
    ErrorLocation location;

}
