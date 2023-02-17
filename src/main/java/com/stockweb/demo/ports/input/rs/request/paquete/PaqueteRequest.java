package com.stockweb.demo.ports.input.rs.request.paquete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteRequest {

    @JsonProperty(value = "nombre_paquete")
    private String nombrePaquete ;

}
