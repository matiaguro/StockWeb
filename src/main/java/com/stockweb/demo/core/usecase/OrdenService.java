package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;

public interface OrdenService {

    Orden createOrden (OrdenRequest ordenRequest);

}
