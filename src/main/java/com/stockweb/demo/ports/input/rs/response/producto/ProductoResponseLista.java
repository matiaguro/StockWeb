package com.stockweb.demo.ports.input.rs.response.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseLista {
    private List<ProductoResponse> content = null;
    @JsonProperty("next_uri")
    private String nextUri;
    @JsonProperty("previous_uri")
    private String previousUri;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("total_elements")
    private Long totalElements;

}
