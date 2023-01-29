package com.stockweb.demo.ports.input.rs.request.producto;

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
public class ProductoRequestAmount {

    @NotNull(message = "Stock cannot be null")
    @JsonProperty("stock")
    private Long stock;

}
