package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.core.model.Product;
import com.stockweb.demo.core.repository.ProductRepository;
import com.stockweb.demo.core.usecase.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Long createEntity(Product product) {
        return productRepository.save(product).getId();
    }

}
