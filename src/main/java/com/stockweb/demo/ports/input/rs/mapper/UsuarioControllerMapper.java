package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.ports.input.rs.response.usuario.UsuarioResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface UsuarioControllerMapper extends CommonMapper{



    @IterableMapping(qualifiedByName = "usuarioToResponseUsuario")
    List<UsuarioResponse> usuarioListaToUsuarioResponseList(List<Usuario> usuarios);


    @Named("usuarioToResponseUsuario")
    UsuarioResponse usuarioToResponseUsuario(Usuario usuario);
}
