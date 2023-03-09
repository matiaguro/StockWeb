package com.stockweb.demo.ports.input.rs.request.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

    @NotBlank(message = "El producto no puede ser nulo")
    @JsonProperty("producto")
    private String producto;

    @NotNull(message = "El stock no puede ser nulo")
    @JsonProperty("stock")
    private Long stock;

    @NotNull(message = "El precio no puede ser nulo")
    @JsonProperty("precio")
    private Long precio;

    @JsonProperty("imgUrl")
    private String imgUrl;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("fechaUltimoInv")
    private Date fechaUltimoInv;

}
