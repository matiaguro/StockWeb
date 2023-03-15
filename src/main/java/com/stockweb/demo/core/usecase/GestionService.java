package com.stockweb.demo.core.usecase;

import java.util.List;

public interface GestionService {

    void setPaqueteOrden (Long idOrden, List<Long> paquetes);

    void actualizarOrden(Long idOrden);

    void setAdelanto (Long idOrden);

    void setPagada (Long idOrden);

    void setFinalizada(Long idOrden);

}
