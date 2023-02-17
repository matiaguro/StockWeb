package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.NotFoundException;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.config.exception.NotProductException;
import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.repository.PaqueteRepository;
import com.stockweb.demo.core.repository.ProductoRepository;
import com.stockweb.demo.core.usecase.PaqueteService;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteProductoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaqueteServiceImpl implements PaqueteService {

    @Value("${app.default.precio-paquete}")
    private Long defaultPrecio;
    private final PaqueteRepository paqueteRepository;

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public Paquete crearPaquete(Paquete paquete) {
        paquete.setPrecioPaquete(defaultPrecio);
        return paqueteRepository.save(paquete);
    }

    @Override
    @Transactional
    public void agregarProducto(Long idPaquete, PaqueteProductoRequest request) {
        paqueteRepository.findById(idPaquete).map(paqueteJpa -> {
            for (Long idProducto : request.getIdProductos()){
                Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new NotFoundException(idPaquete));
                paqueteJpa.setPrecioPaquete(sumarPrecio(paqueteJpa.getPrecioPaquete(), producto.getPrecio()));
                paqueteJpa.addProducto(producto);
            }
            return paqueteRepository.save(paqueteJpa);
        }).orElseThrow(() -> new NotPackage(idPaquete));
    }

    @Override
    @Transactional
    public void sacarProducto(Long idPaquete, PaqueteProductoRequest request) {
        paqueteRepository.findById(idPaquete).map(paqueteJpa -> {
            for (Long idProducto : request.getIdProductos()){
                Producto productoEliminar = paqueteJpa.selectProductoById(idProducto);
                paqueteJpa.setPrecioPaquete(restarPrecio(paqueteJpa.getPrecioPaquete(), productoEliminar.getPrecio()));
                paqueteJpa.deleteProducto(productoEliminar);
            }
            return paqueteRepository.save(paqueteJpa);
        }).orElseThrow(() -> new NotPackage(idPaquete));

    }

    @Override
    @Transactional
    public void deletePaquete(Long idPaquete) {
        paqueteRepository.deleteById(idPaquete);
    }

    @Override
    public Paquete findByid(Long idPaquete) {
        return paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));
    }


    private Long sumarPrecio(Long precioPaquete, Long precioProducto){
        return precioPaquete + precioProducto;
    }
    private Long restarPrecio(Long precioPaquete, Long precioProducto){
        return precioPaquete - precioProducto;
    }

}
