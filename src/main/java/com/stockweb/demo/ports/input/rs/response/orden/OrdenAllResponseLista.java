package com.stockweb.demo.ports.input.rs.response.orden;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenAllResponseLista {

    private List<OrdenAllResponse> content = null;

    @JsonProperty("next_uri")
    private String nextUri;

    @JsonProperty("previous_uri")
    private String previousUri;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

}
