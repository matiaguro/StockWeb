package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.NotOrdenException;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.datetime.Fecha;
import com.stockweb.demo.core.repository.OrdenRepository;
import com.stockweb.demo.core.repository.PaqueteRepository;
import com.stockweb.demo.core.usecase.GestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestionServiceImpl implements GestionService {

    private final OrdenRepository ordenRepository;

    private final PaqueteRepository paqueteRepository;

    public void setPaqueteOrden(Long idOrden, List<Long> paquetes){
            Orden orden = ordenRepository.findById(idOrden).orElseThrow(()->new NotOrdenException(idOrden));
            for (Long idPaquete : paquetes){
                paqueteRepository.findById(idPaquete).map(paqueteJpa -> {
                    paqueteJpa.setOrden(orden);
                    return paqueteRepository.save(paqueteJpa);
                }).orElseThrow(() -> new NotPackage(idPaquete));
            }
            orden.setFechaModificacion(Fecha.get());
            ordenRepository.save(orden);
    }

}
