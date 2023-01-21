package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.ProductList;
import com.stockweb.demo.core.model.Producto;
import org.springframework.data.domain.PageRequest;

public interface ProductoService {

    Long createEntity(Producto producto);

    void updateEntityIfExists(Long idProducto, Producto producto);

    void upDateStock (Long idProducto, Long stock);

    void deleteById(Long idProducto);

    ProductList getLista(PageRequest pageRequest);

    Producto findById(Long idProducto);
    }
