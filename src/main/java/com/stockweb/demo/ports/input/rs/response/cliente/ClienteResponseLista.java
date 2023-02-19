package com.stockweb.demo.ports.input.rs.response.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class ClienteResponseLista {
    private List<ClienteResponse> content = null;
    @JsonProperty("next_uri")
    private String nextUri;

    @JsonProperty("previous_uri")
    private String previousUri;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

}
