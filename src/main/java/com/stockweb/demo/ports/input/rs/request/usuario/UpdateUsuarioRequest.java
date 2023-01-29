package com.stockweb.demo.ports.input.rs.request.usuario;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsuarioRequest {

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("id_rol")
    private Long idRol;

}
