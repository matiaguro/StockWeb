package com.stockweb.demo.ports.input.rs.response.orden;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResponse {

    @JsonProperty ("nombre_usuario")
    private String nombreUsuario;

    @JsonProperty ("nombre_cliente")
    private String nombreCliente;

    @JsonProperty ("clave_estado")
    private String claveEstado;

    @JsonProperty ("fecha_generada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ",timezone = "GMT-3")
    private Date fechaGenerada;
}
