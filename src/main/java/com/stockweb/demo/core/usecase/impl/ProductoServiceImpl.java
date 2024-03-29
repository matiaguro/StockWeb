package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.NotProductException;
import com.stockweb.demo.core.model.ProductList;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.model.datetime.Fecha;
import com.stockweb.demo.core.repository.ProductoRepository;
import com.stockweb.demo.core.usecase.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productRepository;

    @Override
    @Transactional
    public Long createEntity(Producto producto) {
        producto.setFechaUltimoInv(Fecha.get());
        return productRepository.save(producto).getIdProducto();
    }

    @Override
    @Transactional
    public void updateEntityIfExists(Long idProducto, Producto producto) {
        productRepository.findById(idProducto)
                .map(productJpa -> {
                    setProducto(productJpa, producto);
                    return productRepository.save(productJpa);
                }).orElseThrow(() -> new NotProductException(idProducto));
    }
    @Override
    @Transactional
    public void upDateStock(Long idProducto, Long stock){
        productRepository.findById(idProducto)
                .map(productJpa -> {
                    productJpa.setStock(stock);
                    productJpa.setFechaUltimoInv(Fecha.get());
                    return productRepository.save(productJpa);
                }).orElseThrow(() -> new NotProductException(idProducto));
    }
    @Override
    @Transactional
    public void deleteById(Long idProducto){
        productRepository.findById(idProducto).ifPresent(productRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductList getLista(PageRequest pageRequest) {
        Page<Producto> page = productRepository.findAll(pageRequest);
        return new ProductList(page.getContent(), pageRequest, page.getTotalElements());
    }
    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long idProducto){
        return  productRepository.findById(idProducto).orElseThrow(() -> new NotProductException(idProducto));
    }


    private void setProducto(Producto productJpa, Producto producto) {
        if (!producto.getProducto().isEmpty()) {
            productJpa.setProducto(producto.getProducto());
        }
        if (producto.getPrecio() != null) {
            productJpa.setPrecio(producto.getPrecio());
        }
        if (!producto.getDescripcion().isEmpty()) {
            productJpa.setDescripcion(producto.getDescripcion());
        }
        if (!producto.getImgUrl().isEmpty()) {
            productJpa.setImgUrl(producto.getImgUrl());
        }
    }
}
