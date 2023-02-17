package com.stockweb.demo.ports.input.rs.response.paquete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteResponse {
    @JsonProperty("idPaquete")
    private Long idPaquete;

    @JsonProperty("nombrePaquete")
    private String nombrePaquete;

    @JsonProperty("precioPaquete")
    private Long precioPaquete;

}
