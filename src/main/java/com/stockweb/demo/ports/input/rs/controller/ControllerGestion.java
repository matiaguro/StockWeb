package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.usecase.GestionService;
import com.stockweb.demo.ports.input.rs.api.ApiGestion;
import com.stockweb.demo.ports.input.rs.request.gestion.SetPaqueteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.GESTION_URI;

@RestController
@RequestMapping(GESTION_URI)
@RequiredArgsConstructor
public class ControllerGestion  implements ApiGestion {

    private final GestionService gestionService;

    @Override
    @PatchMapping("/setPaquete")
    @ResponseStatus(HttpStatus.OK)
    public void setPaqueteOrden(@Valid @RequestBody SetPaqueteRequest setPaqueteRequest) {
        gestionService.setPaqueteOrden(setPaqueteRequest.getIdOrden(), setPaqueteRequest.getIdPaquetes());
    }
}
