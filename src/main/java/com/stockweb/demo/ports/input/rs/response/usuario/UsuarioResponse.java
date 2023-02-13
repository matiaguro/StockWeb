package com.stockweb.demo.ports.input.rs.response.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockweb.demo.core.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    @JsonProperty("id_usuario")
    private Long idUsuario;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("rol")
    private Rol rol;

}
