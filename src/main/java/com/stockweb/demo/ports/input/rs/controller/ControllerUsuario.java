package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.model.UsuarioList;
import com.stockweb.demo.core.usecase.UsuarioService;
import com.stockweb.demo.ports.input.rs.api.ApiConstants;
import com.stockweb.demo.ports.input.rs.api.ApiUsuario;
import com.stockweb.demo.ports.input.rs.mapper.UsuarioControllerMapper;
import com.stockweb.demo.ports.input.rs.request.usuario.UpdatePasswordRequest;
import com.stockweb.demo.ports.input.rs.request.usuario.UpdateUsuarioRequest;
import com.stockweb.demo.ports.input.rs.response.usuario.UsuarioResponse;
import com.stockweb.demo.ports.input.rs.response.usuario.UsuarioResponseLista;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.USUARIOS_URI;

@RestController
@RequestMapping(USUARIOS_URI)
@RequiredArgsConstructor
public class ControllerUsuario implements ApiUsuario {


    private final UsuarioControllerMapper mapper;
    private  final UsuarioService usuarioService;

    @Override
    @PatchMapping("/updatePassword/{idUsuario}")
    public void updatePassword (@NotNull @PathVariable Long idUsuario, @Valid @RequestBody UpdatePasswordRequest passwordRequest){
        usuarioService.upDatePassword(idUsuario, passwordRequest.getNewPassword());
    }

    @Override
    @DeleteMapping("/{idUsuario}")
    public void deleteUsuario(@NotNull @PathVariable Long idUsuario){
        usuarioService.deleteById(idUsuario);
    }

    @Override
    @PatchMapping("/editUsuario/{idUsuario}")
    public void upDateUsuario(@NotNull @PathVariable Long idUsuario, @RequestBody UpdateUsuarioRequest updateUsuarioRequest){
        Usuario usuario = mapper.updateUsuarioRequestToUser(updateUsuarioRequest);
        usuarioService.updateUsuarioIfExists(idUsuario,usuario,updateUsuarioRequest.getIdRol());
    }



    @Override
    @GetMapping("getAllUsuarios")
    public ResponseEntity<UsuarioResponseLista> getAllUsuarios(@RequestParam Optional<Integer> page,
                                                               @RequestParam Optional<Integer> size){

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        UsuarioList list = usuarioService.getLista(PageRequest.of(pageNumber,pageSize));
        UsuarioResponseLista response;

        {
            response = new UsuarioResponseLista();
            List<UsuarioResponse> contenido = mapper.usuarioListaToUsuarioResponseList(list.getContent());
            response.setContent(contenido);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());}
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/byId/{idUsuario}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@NotNull @PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.getUserById(idUsuario);
        UsuarioResponse response = mapper.usuarioToResponseUsuario(usuario);
        return ResponseEntity.ok().body(response);
    }
}



















