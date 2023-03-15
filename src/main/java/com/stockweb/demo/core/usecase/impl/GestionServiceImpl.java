package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotOrdenException;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.core.model.DescPaquete;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.core.model.datetime.Fecha;
import com.stockweb.demo.core.repository.EstadoOrdenRepository;
import com.stockweb.demo.core.repository.OrdenRepository;
import com.stockweb.demo.core.repository.PaqueteRepository;
import com.stockweb.demo.core.repository.ProductoRepository;
import com.stockweb.demo.core.usecase.GestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestionServiceImpl implements GestionService {

    private final OrdenRepository ordenRepository;

    private final PaqueteRepository paqueteRepository;


    private final ProductoRepository productoRepository;

    private final EstadoOrdenRepository estadoOrdenRepository;


    //TODO AGREGAR METODO DESASIGNAR PAQUETE ORDEN

    @Override
    @Transactional
    public void setPaqueteOrden(Long idOrden, List<Long> paquetes) {
        Orden orden = ordenRepository.findById(idOrden).orElseThrow(() -> new NotOrdenException(idOrden));

        if (orden.getEstadoOrden().getIdEstado() != 1)
            throw new ErrorExpected("Para asignar un paquete la orden debe estar en Estado Generada", HttpStatus.BAD_REQUEST);

        for (Long idPaquete : paquetes) {
            paqueteRepository.findById(idPaquete).map(paqueteJpa -> {

                if (paqueteJpa.getOrden() != null)
                    throw new ErrorExpected("El paquete ya tiene una orden asignada", HttpStatus.BAD_REQUEST);

                paqueteJpa.setOrden(orden);
                return paqueteRepository.save(paqueteJpa);
            }).orElseThrow(() -> new NotPackage(idPaquete));
        }
        orden.setFechaModificacion(Fecha.get());
        ordenRepository.save(orden);
    }

    @Override
    @Transactional
    public void actualizarOrden(Long idOrden) {
        ordenRepository.findById(idOrden).map(ordenJpa -> {

            if (ordenJpa.getEstadoOrden().getIdEstado() != 1)
                throw new ErrorExpected("Para actualizar el precio la orden debe estar en Estado Generada", HttpStatus.BAD_REQUEST);


            float precio = 0;
            for (Paquete paquete : ordenJpa.getPaquetes()) {
                for (DescPaquete descPaquete : paquete.getDescPaqueteList()) {
                    precio += descPaquete.getProducto().getPrecio() * descPaquete.getCantidad();
                }
            }

            ordenJpa.setPrecioTotal(precio);
            return ordenRepository.save(ordenJpa);
        }).orElseThrow(() -> new NotOrdenException(idOrden));
    }

    @Override
    @Transactional
    public void setAdelanto(Long idOrden) {
        ordenRepository.findById(idOrden).map(ordenJpa -> {
            ordenJpa.validarContenido();
            restarStock(ordenJpa);

            ordenJpa.setFechaModificacion(Fecha.get());
            ordenJpa.setEstadoOrden(estadoOrdenRepository.findById(2L).orElseThrow());
            ordenJpa.setAdelanto(true);

            return ordenRepository.save(ordenJpa);
        }).orElseThrow(() -> new NotOrdenException(idOrden));
    }

    @Override
    @Transactional
    public void setPagada(Long idOrden) {
        ordenRepository.findById(idOrden).map(ordenJpa -> {

            if (ordenJpa.getEstadoOrden().getIdEstado() != 1 && ordenJpa.getEstadoOrden().getIdEstado() != 2)
                throw new ErrorExpected("La orden debe estar en estado GENERADA", HttpStatus.BAD_REQUEST);

            if (!ordenJpa.getAdelanto()) {
                ordenJpa.validarContenido();
                restarStock(ordenJpa);
            }

            ordenJpa.setFechaModificacion(Fecha.get());
            ordenJpa.setEstadoOrden(estadoOrdenRepository.findById(3L).orElseThrow());

            return ordenRepository.save(ordenJpa);
        }).orElseThrow(() -> new NotOrdenException(idOrden));
    }

    @Override
    @Transactional
    public void setFinalizada(Long idOrden) {
        ordenRepository.findById(idOrden).map( ordenJpa -> {

            if (ordenJpa.getEstadoOrden().getIdEstado() != 3)
                throw new ErrorExpected("La orden debe estar en estado PAGADA", HttpStatus.BAD_REQUEST);

            ordenJpa.setFechaFinalizada(Fecha.get());
            ordenJpa.setEstadoOrden(estadoOrdenRepository.findById(4L).orElseThrow());

            return ordenRepository.save(ordenJpa);
        }).orElseThrow(() -> new NotOrdenException(idOrden));

    }


    private void restarStock(Orden orden) {

        if (orden.getEstadoOrden().getIdEstado() != 1)
            throw new ErrorExpected("La orden debe estar en estado GENERADA", HttpStatus.BAD_REQUEST);

        for (Paquete paquete : orden.getPaquetes()) {
            for (DescPaquete descPaquete : paquete.getDescPaqueteList()) {
                productoRepository.findById(descPaquete.getProducto().getIdProducto()).map(productoJpa -> {

                    if (!(productoJpa.getStock() >= descPaquete.getCantidad()))
                        throw new ErrorExpected("No hay stock del producto: " + productoJpa.getProducto() , HttpStatus.BAD_REQUEST);

                    productoJpa.setStock(productoJpa.getStock() - descPaquete.getCantidad());
                    return productoRepository.save(productoJpa);
                }).orElseThrow(() -> new NotPackage(descPaquete.getProducto().getIdProducto()));
            }
        }
    }

}
