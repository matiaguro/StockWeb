package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.core.model.Product;
import com.stockweb.demo.core.usecase.ProductService;
import com.stockweb.demo.ports.input.rs.api.ProductApi;
import com.stockweb.demo.ports.input.rs.mapper.ProductControllerMapper;
import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.stockweb.demo.ports.input.rs.api.ApiConstans.PRODUCT_URI;

@RestController
@RequestMapping(PRODUCT_URI)
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductControllerMapper mapper;

    private  final ProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        Product product = mapper.productRequestToProduct(productRequest);

        final long id = productService.createEntity(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
