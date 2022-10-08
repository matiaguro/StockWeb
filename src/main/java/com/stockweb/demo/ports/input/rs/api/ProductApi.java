package com.stockweb.demo.ports.input.rs.api;


import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Validated
public interface ProductApi {

    ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequest productRequest);


}