package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.core.model.Product;
import com.stockweb.demo.core.usecase.ProductService;
import com.stockweb.demo.ports.input.rs.api.ProductApi;
import com.stockweb.demo.ports.input.rs.mapper.ProductControllerMapper;
import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import com.stockweb.demo.ports.input.rs.request.ProductRequestAmount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDateProduct(@NotNull @PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        Product product = mapper.productRequestToProduct(productRequest);
        productService.updateEntityIfExists(id, product);
    }
    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDateAmount(@NotNull @PathVariable Long id, @Valid @RequestBody ProductRequestAmount productRequestAmount) {
        Long amount = productRequestAmount.getAmount();
        productService.upDateAmount(id, amount);

    }
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteProduct(@NotNull @PathVariable Long id){
        productService.deleteById(id);

    }



}
