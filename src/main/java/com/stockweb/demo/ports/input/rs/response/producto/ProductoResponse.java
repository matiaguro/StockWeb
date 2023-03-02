package com.stockweb.demo.ports.input.rs.response.producto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private Long idProducto;

    @JsonProperty("producto")
    private String producto;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("stock")
    private Long stock;

    @JsonProperty("fechaUltimoInv")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ")
    private Date fechaUltimoInv;

    @JsonProperty("precio")
    private Long precio;

    @JsonProperty("imgUrl")
    private String imgUrl;


}
