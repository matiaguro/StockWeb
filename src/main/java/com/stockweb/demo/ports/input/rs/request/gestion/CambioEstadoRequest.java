package com.stockweb.demo.ports.input.rs.request.gestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioEstadoRequest {

    @NotNull
    @JsonProperty("idOrden")
    private  Long idOrden;

    @NotNull
    @JsonProperty("nuevoEstado")
    private String nuevoEstado;

}
