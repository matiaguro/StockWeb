package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.usecase.OrdenService;
import com.stockweb.demo.ports.input.rs.api.ApiOrden;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                .nombreUsuario(orden.getUsuario().getFirstname() + orden.getUsuario().getLastname())
                .claveEstado(orden.getEstadoOrden().getClaveEstado())
                .nombreCliente(orden.getCliente().getFirstname() + orden.getCliente().getLastname())
                .fechaGenerada(orden.getFechaGenerada()).build();

        return ResponseEntity.ok().body(ordenResponse);
    }


}
