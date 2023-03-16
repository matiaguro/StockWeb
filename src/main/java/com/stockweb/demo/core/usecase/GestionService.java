package com.stockweb.demo.core.usecase;

import java.util.List;

public interface GestionService {

    void removePaqueteOrden(Long idOrden, List<Long> paquetes);
    void setPaqueteOrden (Long idOrden, List<Long> paquetes);

    void actualizarOrden(Long idOrden);

    void setAdelanto (Long idOrden);

    void setPagada (Long idOrden);

    void setFinalizada(Long idOrden);

}
