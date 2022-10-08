package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Product;

public interface ProductService {

    Long createEntity(Product product);

    void updateEntityIfExists(Long id, Product product);

    void upDateAmount (Long id, Long amount);


}
