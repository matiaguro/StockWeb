package com.stockweb.demo.ports.input.rs.request.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    @NotBlank(message = "firstname cannot be null")
    @JsonProperty("firstname")
    private String firstname;

    @NotBlank(message = "lastname cannot be null")
    @JsonProperty("lastname")
    private String lastname;

    @NotBlank(message = "email cannot be null")
    @JsonProperty("email")
    private String email;


}
