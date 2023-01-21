package com.stockweb.demo.ports.input.rs.api;


import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import com.stockweb.demo.ports.input.rs.request.ProductoRequestAmount;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductApi {

    ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequest productRequest);

    public void upDateProduct(@NotNull @PathVariable Long id, @Valid @RequestBody ProductRequest productRequest);

    public void upDateStock(@NotNull @PathVariable Long id, @Valid @RequestBody ProductoRequestAmount productoRequestAmount);

    public void DeleteProduct(@NotNull @PathVariable Long id);
}