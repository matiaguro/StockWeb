package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Producto;

import java.util.List;

public interface ProductService {

    Long createEntity(Producto product);

    void updateEntityIfExists(Long id, Producto product);

    void upDateAmount (Long id, Long amount);

    void deleteById(Long id);

    List<Producto> findByName (String name);


    }
