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

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("rol")
    private Rol rol;

}
