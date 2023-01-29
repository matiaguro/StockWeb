package com.stockweb.demo.ports.input.rs.request.usuario;


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
public class UpdatePasswordRequest {

    @NotNull(message = "NewPassword cannot be null")
    @JsonProperty("newPassword")
    private String newPassword;

}
