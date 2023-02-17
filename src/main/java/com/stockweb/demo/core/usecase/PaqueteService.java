package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteProductoRequest;

public interface PaqueteService {

    Paquete crearPaquete(Paquete paquete);

    void agregarProducto (Long idPaquete, PaqueteProductoRequest request) throws Exception;

    void sacarProducto (Long idPaquete, PaqueteProductoRequest request);

    void deletePaquete(Long idPaquete);

    Paquete findByid (Long idPaquete);
}
