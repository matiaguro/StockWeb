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


    @Override
    @Transactional
    public void removePaqueteOrden(Long idOrden, List<Long> paquetes) {
        Orden orden = ordenRepository.findById(idOrden).orElseThrow(() -> new NotOrdenException(idOrden));
        orden.validEstadoGenerada("Para desasignar un paquete, la orden debe estar en Estado Generada");

        for (Long idPaquete : paquetes){
            paqueteRepository.findById(idPaquete).map(paqueteJpa ->{
                paqueteJpa.validOrden();
                if (paqueteJpa.getOrden().getIdOrden().equals(idOrden)){
                    paqueteJpa.setOrden(null);
                }
                else {
                    throw new ErrorExpected("El paquete "+idPaquete+ "no tiene asignada la orden "+idOrden ,HttpStatus.BAD_REQUEST);
                }
                return paqueteRepository.save(paqueteJpa);
            }).orElseThrow(() -> new NotPackage(idPaquete));
        }

        orden.setFechaModificacion(Fecha.get());
        ordenRepository.save(orden);
    }

    @Override
    @Transactional
    public void setPaqueteOrden(Long idOrden, List<Long> paquetes) {
        Orden orden = ordenRepository.findById(idOrden).orElseThrow(() -> new NotOrdenException(idOrden));
        orden.validEstadoGenerada("Para asignar un paquete la orden debe estar en Estado Generada");

        for (Long idPaquete : paquetes) {
            paqueteRepository.findById(idPaquete).map(paqueteJpa -> {

                if (paqueteJpa.getOrden() != null){
                    if (paqueteJpa.getOrden().equals(orden))
                        throw new ErrorExpected("El paquete ya tiene esta orden asignada", HttpStatus.BAD_REQUEST);

                    throw new ErrorExpected("El paquete ya tiene una orden asignada", HttpStatus.BAD_REQUEST);
                }

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

            ordenJpa.validEstadoGenerada("Para actualizar el precio la orden debe estar en Estado Generada");

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

    @Override
    @Transactional
    public void setDevolucion(Long idOrden) {

        ordenRepository.findById(idOrden).map( ordenJpa -> {

            if (ordenJpa.getEstadoOrden().getIdEstado() == 1)
                throw new ErrorExpected("La orden esta en estado Generada, solo se puede cancelar", HttpStatus.BAD_REQUEST);

            else {

                devolverStock (ordenJpa);
                ordenJpa.setEstadoOrden(estadoOrdenRepository.findById(6L).orElseThrow());
                ordenJpa.setFechaDevolucion(Fecha.get());

            }

            return ordenRepository.save(ordenJpa);

        }).orElseThrow(() -> new NotOrdenException(idOrden));

    }

    @Override
    @Transactional
    public void setCancelada(Long idOrden) {


        ordenRepository.findById(idOrden).map( ordenJpa -> {

            if (ordenJpa.getEstadoOrden().getIdEstado() != 1)
                throw new ErrorExpected("La orden esta en estado Generada, solo se puede cancelar", HttpStatus.BAD_REQUEST);

            else {

                devolverStock (ordenJpa);
                ordenJpa.setEstadoOrden(estadoOrdenRepository.findById(5L).orElseThrow());
                ordenJpa.setFechaFinalizada(Fecha.get());

            }

            return ordenRepository.save(ordenJpa);

        }).orElseThrow(() -> new NotOrdenException(idOrden));

    }


    private void restarStock(Orden orden) {
        orden.validEstadoGenerada("La orden debe estar en estado GENERADA");

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

    private void devolverStock (Orden orden){

        for (Paquete paquete : orden.getPaquetes()){
            for (DescPaquete descPaquete : paquete.getDescPaqueteList()){

                productoRepository.findById(descPaquete.getProducto().getIdProducto()).map(productoJpa -> {

                    productoJpa.setStock(productoJpa.getStock() + descPaquete.getCantidad());
                    return productoRepository.save(productoJpa);

                });

            }
        }


    }


}
