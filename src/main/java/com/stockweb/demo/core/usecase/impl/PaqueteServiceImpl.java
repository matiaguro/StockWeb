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
import com.stockweb.demo.ports.input.rs.request.paquete.AddOrRemoveProductRequest;
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

    @Override
    @Transactional
    public Paquete crearPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    @Transactional
    public void agregarProducto(Long idPaquete, List<AddOrRemoveProductRequest> paqueteRequest) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));
        for(AddOrRemoveProductRequest addProduct : paqueteRequest){
            Producto producto = productoRepository.findById(addProduct.getIdProducto()).orElseThrow(() -> new NotProductException(addProduct.getIdProducto()));
            if (!descPaqueteRepository.existsByPaqueteAndProducto(paquete,producto)){
                DescPaquete descPaquete = new DescPaquete();
                descPaquete.setProducto(producto);
                descPaquete.setPaquete(paquete);
                descPaquete.setCantidad(addProduct.getCantidad());
                descPaqueteRepository.save(descPaquete);
            }else {
                throw new ErrorExpected("El producto ya esta agregado, la modifique cantidad", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    @Transactional
    public void sacarProducto(Long idPaquete,  List<AddOrRemoveProductRequest> paqueteRequest) {
        Paquete paquete = paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotPackage(idPaquete));
        for(AddOrRemoveProductRequest addProduct : paqueteRequest){
            Producto producto = productoRepository.findById(addProduct.getIdProducto()).orElseThrow(() -> new NotProductException(addProduct.getIdProducto()));
            if (descPaqueteRepository.existsByPaqueteAndProducto(paquete,producto)){
                DescPaquete descPaquete = descPaqueteRepository.findByPaqueteAndProducto(paquete,producto);
                descPaqueteRepository.deleteById(descPaquete.getIdDescPaquete());
            }else {
                throw new ErrorExpected("El producto de id: "+producto.getIdProducto()+" no se encuentra dentro del paquete con id: "+paquete.getIdPaquete(), HttpStatus.BAD_REQUEST);
            }
        }

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


}
