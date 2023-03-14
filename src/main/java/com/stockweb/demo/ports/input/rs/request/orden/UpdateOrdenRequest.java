package com.stockweb.demo.ports.input.rs.request.orden;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrdenRequest {


    @JsonProperty ("idUser")
    private Long idUser;


}
