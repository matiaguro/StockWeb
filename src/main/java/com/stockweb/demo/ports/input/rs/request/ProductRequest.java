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

    @NotBlank(message = "Name cannot be null")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Amount cannot be null")
    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("description")
    private String description;

}
