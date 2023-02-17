package com.stockweb.demo.ports.input.rs.request.paquete;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteProductoRequest {

    @JsonProperty(value = "productos")
    private List<Long> idProductos;

}
