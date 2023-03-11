package com.stockweb.demo.ports.input.rs.request.orden;

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
public class OrdenRequest {

    @NotNull(message = "idCliente cannot be null")
    @JsonProperty("idCliente")
    private Long idCliente;

}
