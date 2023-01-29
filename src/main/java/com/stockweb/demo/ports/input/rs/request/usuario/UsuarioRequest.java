package com.stockweb.demo.ports.input.rs.request.usuario;


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
public class UsuarioRequest {

    @NotBlank(message = "Usuario cannot be null")
    @JsonProperty("usuario")
    private String usuario;

    @NotNull(message = "Password cannot be null")
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("id_rol")
    private Long idRol;

}
