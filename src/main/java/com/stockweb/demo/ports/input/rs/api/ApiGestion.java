package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.ports.input.rs.request.gestion.CambioEstadoRequest;
import com.stockweb.demo.ports.input.rs.request.gestion.SetPaqueteRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ApiGestion {

    void setPaqueteOrden (@Valid @RequestBody SetPaqueteRequest setPaqueteRequest);

    void setPaqueteOrden(@NotNull @PathVariable Long idOrden);

    void cambioEstado(@Valid @RequestBody CambioEstadoRequest request);


}
