package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.ports.input.rs.request.usuario.UpdatePasswordRequest;
import com.stockweb.demo.ports.input.rs.request.usuario.UpdateUsuarioRequest;
import com.stockweb.demo.ports.input.rs.request.usuario.UsuarioRequest;
import com.stockweb.demo.ports.input.rs.response.usuario.UsuarioResponseLista;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface ApiUsuario {

    ResponseEntity<Void> crearUsuario (@Valid @RequestBody UsuarioRequest usuarioRequest);

    void updatePassword (@NotNull @PathVariable Long idUsuario, @Valid @RequestBody UpdatePasswordRequest passwordRequest);

    void DeleteProducto(@NotNull @PathVariable Long idUsuario);

    void upDateUsuario(@NotNull @PathVariable Long idUsuario, @Valid @RequestBody UpdateUsuarioRequest updateUsuarioRequest);

    ResponseEntity<UsuarioResponseLista> getAllUsuarios(@RequestParam Optional<Integer> page,
                                                        @RequestParam Optional<Integer> size);

}
