package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.usecase.OrdenService;
import com.stockweb.demo.ports.input.rs.api.ApiOrden;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.request.orden.UpdateOrdenRequest;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.ORDEN_URI;


@RestController
@RequestMapping(ORDEN_URI)
@RequiredArgsConstructor
public class ControllerOrden implements ApiOrden {

    private final OrdenService ordenService;

    @Override
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<OrdenResponse> createOrden(@Valid @RequestBody OrdenRequest ordenRequest) {

        Orden orden = ordenService.createOrden(ordenRequest);
        OrdenResponse ordenResponse = OrdenResponse.builder()
                .nombreUsuario(orden.getUsuario().getFirstname() + " " + orden.getUsuario().getLastname())
                .claveEstado(orden.getEstadoOrden().getClaveEstado())
                .nombreCliente(orden.getCliente().getFirstname() + " " + orden.getCliente().getLastname())
                .fechaGenerada(orden.getFechaGenerada()).build();

        return ResponseEntity.ok().body(ordenResponse);
    }
    @Override
    @DeleteMapping ("/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public void deleteOrden(Long idOrden) {
        ordenService.deleteOrden(idOrden);
    }

    @Override
    @PatchMapping ("editOrden/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Void> uptadeOrdenAdmin(@NotNull @PathVariable Long idOrden, @Valid @RequestBody UpdateOrdenRequest updateOrdenRequest) {

        Orden orden = ordenService.updateEntityIfExists (idOrden, updateOrdenRequest.getIdUser());
        ordenService.updateFechaUltimaModificacion (idOrden);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/byId/{idOrden}")
                .buildAndExpand(idOrden)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @PatchMapping
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<OrdenResponse> updateOrdenUser(Long idOrden, Optional<Cliente> cliente, Optional<String> descripcion) {

        //acordarse de modificar la fecha de modificacion

        return null;
    }



}
