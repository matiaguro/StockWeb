package com.stockweb.demo.ports.input.rs.request.paquete;

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
public class AddOrRemoveProductRequest {

    @NotNull
    @JsonProperty(value = "id_producto")
    private Long idProducto;

    @NotNull
    @JsonProperty(value = "cantidad")
    private Long cantidad;


}
