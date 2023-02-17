package com.stockweb.demo.ports.input.rs.response.paquete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteProductoResponse {

    @JsonProperty("idPaquete")
    private Long idPaquete;

    @JsonProperty("nombrePaquete")
    private String nombrePaquete;

    @JsonProperty("precioPaquete")
    private Long precioPaquete;

    @JsonProperty("productos")
    private List<ProductoResponse> productos;

}
