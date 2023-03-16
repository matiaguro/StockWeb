package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.core.usecase.GestionService;
import com.stockweb.demo.ports.input.rs.api.ApiGestion;
import com.stockweb.demo.ports.input.rs.request.gestion.CambioEstadoRequest;
import com.stockweb.demo.ports.input.rs.request.gestion.SetPaqueteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.GESTION_URI;

@RestController
@RequestMapping(GESTION_URI)
@RequiredArgsConstructor
public class ControllerGestion  implements ApiGestion {

    private final GestionService gestionService;

    @Override
    @PatchMapping("/removePaquete")
    @ResponseStatus(HttpStatus.OK)
    public void removePaqueteOrden(@Valid @RequestBody SetPaqueteRequest setPaqueteRequest) {
        gestionService.removePaqueteOrden(setPaqueteRequest.getIdOrden(), setPaqueteRequest.getIdPaquetes());

    }

    @Override
    @PatchMapping("/setPaquete")
    @ResponseStatus(HttpStatus.OK)
    public void setPaqueteOrden(@Valid @RequestBody SetPaqueteRequest setPaqueteRequest) {
        gestionService.setPaqueteOrden(setPaqueteRequest.getIdOrden(), setPaqueteRequest.getIdPaquetes());
    }


    @Override
    @PatchMapping("/actualizarOrden/{idOrden}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarOrden(@NotNull @PathVariable Long idOrden) {
        gestionService.actualizarOrden(idOrden);
    }

    @Override
    @PatchMapping("/cambioEstado")
    @ResponseStatus(HttpStatus.OK)
    public void cambioEstado(@Valid @RequestBody CambioEstadoRequest request) {

        switch (request.getNuevoEstado()) {
            case "ADELANTO" -> gestionService.setAdelanto(request.getIdOrden());
            case "PAGADA" -> gestionService.setPagada(request.getIdOrden());
            case "FINALIZADA" -> gestionService.setFinalizada(request.getIdOrden());
            case "DEVOLUCION" -> throw new ErrorExpected("DEVOLUCION", HttpStatus.OK);
            case "CANCELADA" -> throw new ErrorExpected("CANCELADA", HttpStatus.OK);
            default -> throw new ErrorExpected("Estado no contemplado", HttpStatus.BAD_REQUEST);
        }



    }
}
