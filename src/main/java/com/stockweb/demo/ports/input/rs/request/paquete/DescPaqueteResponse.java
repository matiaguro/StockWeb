package com.stockweb.demo.ports.input.rs.request.paquete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponse;
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
public class DescPaqueteResponse {

    @JsonProperty("producto")
    private ProductoResponse producto;

    @JsonProperty("cantidad")
    private Long cantidad;
}
