package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.ports.input.rs.request.usuario.UpdateUsuarioRequest;
import com.stockweb.demo.ports.input.rs.response.authentication.RegisterRequest;
import com.stockweb.demo.ports.input.rs.response.usuario.UsuarioResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface UsuarioControllerMapper extends CommonMapper{

    Usuario registerRequestToUser(RegisterRequest request);

    @IterableMapping(qualifiedByName = "usuarioToResponseUsuario")
    List<UsuarioResponse> usuarioListaToUsuarioResponseList(List<Usuario> usuarios);


    @Named("usuarioToResponseUsuario")
    UsuarioResponse usuarioToResponseUsuario(Usuario usuario);

    @Mapping(target = "rol", ignore = true)
    Usuario updateUsuarioRequestToUser(UpdateUsuarioRequest updateUsuarioRequest);
}
