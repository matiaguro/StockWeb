package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.config.exception.NotProductException;
import com.stockweb.demo.core.model.DescPaquete;
import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.repository.DescPaqueteRepository;
import com.stockweb.demo.core.repository.PaqueteRepository;
import com.stockweb.demo.core.repository.ProductoRepository;
import com.stockweb.demo.core.usecase.PaqueteService;
import com.stockweb.demo.ports.input.rs.mapper.PaqueteControllerMapper;
import com.stockweb.demo.ports.input.rs.request.paquete.AddOrRemoveProductRequest;
import com.stockweb.demo.ports.input.rs.request.paquete.DescPaqueteResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaqueteServiceImpl implements PaqueteService {

    private final PaqueteRepository paqueteRepository;

    private final ProductoRepository productoRepository;

    private final DescPaqueteRepository descPaqueteRepository;

    private final PaqueteControllerMapper mapper;

    @Override
    @Transactional
    public Paquete crearPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    @Transactional
    public void agregarProducto(Long idPaquete, List<AddOrRemoveProductRequest> paqueteRequest) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));

        if(paquete.getOrden() != null && paquete.getOrden().getEstadoOrden().getIdEstado() != 1)
            throw new ErrorExpected("No se puede agregar productos si la orden asignada al paquete no esta en estado GENERADA", HttpStatus.BAD_REQUEST);


        for(AddOrRemoveProductRequest addProduct : paqueteRequest){
            Producto producto = productoRepository.findById(addProduct.getIdProducto()).orElseThrow(() -> new NotProductException(addProduct.getIdProducto()));
            if (!descPaqueteRepository.existsByPaqueteAndProducto(paquete,producto)){
                DescPaquete descPaquete = new DescPaquete();
                descPaquete.setProducto(producto);
                descPaquete.setPaquete(paquete);
                descPaquete.setCantidad(addProduct.getCantidad());
                descPaqueteRepository.save(descPaquete);
            }else {
                throw new ErrorExpected("El producto ya esta agregado, modifique cantidad", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    @Transactional
    public void sacarProducto(Long idPaquete,  List<AddOrRemoveProductRequest> paqueteRequest) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));

        if(paquete.getOrden() != null && paquete.getOrden().getEstadoOrden().getIdEstado() != 1)
            throw new ErrorExpected("No se puede sacar productos si la orden asignada al paquete no esta en estado GENERADA", HttpStatus.BAD_REQUEST);

        for(AddOrRemoveProductRequest addProduct : paqueteRequest){
            Producto producto = productoRepository.findById(addProduct.getIdProducto()).orElseThrow(() -> new NotProductException(addProduct.getIdProducto()));
                DescPaquete descPaquete = descPaqueteRepository.findByPaqueteAndProducto(paquete,producto).orElseThrow(() -> new ErrorExpected("El producto de id: "+producto.getIdProducto()+" no se encuentra dentro del paquete con id: "+paquete.getIdPaquete(), HttpStatus.BAD_REQUEST));
                descPaqueteRepository.deleteById(descPaquete.getIdDescPaquete());
        }
    }

    @Override
    @Transactional
    public void modCantidad(Long idPaquete, List<AddOrRemoveProductRequest> paqueteRequest) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));
        for(AddOrRemoveProductRequest modProduct : paqueteRequest){
            Producto producto = productoRepository.findById(modProduct.getIdProducto()).orElseThrow(() -> new NotProductException(modProduct.getIdProducto()));
            descPaqueteRepository.findByPaqueteAndProducto(paquete,producto).map(modProductJpa -> {
                modProductJpa.setCantidad(modProduct.getCantidad());
                return descPaqueteRepository.save(modProductJpa);
            }).orElseThrow(() ->new ErrorExpected("El producto de id: "+producto.getIdProducto()+" no se encuentra dentro del paquete con id: "+paquete.getIdPaquete(), HttpStatus.BAD_REQUEST));
        }

    }

    @Override
    @Transactional
    public void deletePaquete(Long idPaquete) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));

        if(paquete.getOrden() != null && paquete.getOrden().getEstadoOrden().getIdEstado() != 1)
            throw new ErrorExpected("No es posible eliminar el paquete, la orden asignada al mismo esta fuera del estado GENERADA ", HttpStatus.BAD_REQUEST);

        paqueteRepository.deleteById(idPaquete);
    }

    @Override
    @Transactional (readOnly = true)
    public PaqueteProductoResponse findByid(Long idPaquete) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));
        PaqueteProductoResponse paqueteProductoResponse = mapper.paqueteToPaqueteProductoResponse(paquete);
        long precio = 0;
        for (DescPaqueteResponse desc : paqueteProductoResponse.getProductos()){
            precio += desc.getProducto().getPrecio() * desc.getCantidad();
        }
        paqueteProductoResponse.setPrecioPaquete(precio);
         return paqueteProductoResponse;
    }
}
