package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.NotFoundException;
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



    public void updateEntityIfExists(Long id, Product product) {
        productRepository.findById(id)
                .map(productJpa -> {
                    productJpa.setName(product.getName());
                    productJpa.setAmount(product.getAmount());
                    productJpa.setDescription(product.getDescription());
                    return productRepository.save(productJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void upDateAmount (Long id, Long amount){
        productRepository.findById(id)
                .map(productJpa -> {
                    productJpa.setAmount(amount);
                    return productRepository.save(productJpa);
                }).orElseThrow(() -> new NotFoundException(id));

    }

    public void deleteById(Long id){
        productRepository.findById(id).ifPresent(productRepository::delete);
    }

}
