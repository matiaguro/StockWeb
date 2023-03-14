package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;

import java.util.Optional;

public interface OrdenService {

    Orden createOrden (OrdenRequest ordenRequest);

    Void deleteOrden (Long idOrden);

    Orden updateEntityIfExists (Long idOrden, Long idUser);

    void updateFechaUltimaModificacion (Long idOrden);





}
