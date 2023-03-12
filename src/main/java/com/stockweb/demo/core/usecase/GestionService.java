package com.stockweb.demo.core.usecase;

import com.stockweb.demo.ports.input.rs.request.gestion.CambioEstadoRequest;

import java.util.List;

public interface GestionService {

    void setPaqueteOrden (Long idOrden, List<Long> paquetes);

    void actualizarOrden(Long idOrden);

    void setAdelanto (Long idOrden);

}
