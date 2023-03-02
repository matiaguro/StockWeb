package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.ports.input.rs.request.paquete.AddOrRemoveProductRequest;

import java.util.List;

public interface PaqueteService {

    Paquete crearPaquete(Paquete paquete);

    void agregarProducto (Long idPaquete, List<AddOrRemoveProductRequest> paqueteRequest) throws Exception;

    void sacarProducto (Long idPaquete,  List<AddOrRemoveProductRequest> paqueteRequest);

    void deletePaquete(Long idPaquete);

    Paquete findByid (Long idPaquete);
}
