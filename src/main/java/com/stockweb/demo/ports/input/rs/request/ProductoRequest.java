package com.stockweb.demo.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Producto cannot be null")
    @JsonProperty("producto")
    private String producto;

    @NotNull(message = "Stock cannot be null")
    @JsonProperty("stock")
    private Long stock;

    @JsonProperty("descripcion")
    private String descripcion;

}
