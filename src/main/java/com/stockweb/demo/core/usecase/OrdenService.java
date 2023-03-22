package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.OrdenList;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.request.orden.UpdateOrdenRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;

public interface OrdenService {

    Orden createOrden (OrdenRequest ordenRequest);

    void deleteOrden (Long idOrden);

    void updateEntityIfExists (Long idOrden, UpdateOrdenRequest updateOrdenRequest);

    Orden findById (Long idOrden);

    void updateOrdenUser (Long idOrden, UpdateOrdenRequest updateOrdenRequest);

    OrdenList getLista (PageRequest pageRequest);

}
