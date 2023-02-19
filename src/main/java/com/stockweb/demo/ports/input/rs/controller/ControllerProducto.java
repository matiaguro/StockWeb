package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.core.model.ProductList;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.usecase.ProductoService;
import com.stockweb.demo.ports.input.rs.api.ApiConstants;
import com.stockweb.demo.ports.input.rs.api.ApiProducto;
import com.stockweb.demo.ports.input.rs.mapper.ProductoControllerMapper;
import com.stockweb.demo.ports.input.rs.request.producto.ProductoRequest;
import com.stockweb.demo.ports.input.rs.request.producto.ProductoRequestAmount;
import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponse;
import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponseLista;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.PRODUCT_URI;

@RestController
@RequestMapping(PRODUCT_URI)
@RequiredArgsConstructor
public class ControllerProducto implements ApiProducto {

    private final ProductoControllerMapper mapper;

    private  final ProductoService productoService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createProducto(@Valid @RequestBody ProductoRequest productoRequest) {
        Producto producto = mapper.productoRequestToProducto(productoRequest);
        final long idProducto = productoService.createEntity(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/byId/{id}").buildAndExpand(idProducto)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("/editProducto/{idProducto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDateProducto(@NotNull @PathVariable Long idProducto, @RequestBody ProductoRequest productoRequest) {
        Producto producto = mapper.productoRequestToProducto(productoRequest);
        productoService.updateEntityIfExists(idProducto, producto);
    }
    @Override
    @PatchMapping("/{idProducto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upDateStock(@NotNull @PathVariable Long idProducto, @Valid @RequestBody ProductoRequestAmount productoRequestAmount) {
        productoService.upDateStock(idProducto, productoRequestAmount.getStock());
    }
    @Override
    @DeleteMapping("/{idProducto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteProducto(@NotNull @PathVariable Long idProducto){
        productoService.deleteById(idProducto);

    }

    @Override
    @GetMapping("/allProductos")
    public ResponseEntity<ProductoResponseLista> getAllProductos(@RequestParam Optional<Integer> page,
                                                            @RequestParam Optional<Integer> size) {

       final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
       final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

       ProductList list = productoService.getLista(PageRequest.of(pageNumber,pageSize));

       ProductoResponseLista response;
       {
           response = new ProductoResponseLista();
           List<ProductoResponse> contenido = mapper.productoListaToProductoResponseList(list.getContent());
           response.setContent(contenido);

           final int nextPage = list.getPageable().next().getPageNumber();
           response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

           final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
           response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

           response.setTotalPages(list.getTotalPages());
           response.setTotalElements(list.getTotalElements());}
       return ResponseEntity.ok().body(response);}
    @Override
    @GetMapping("/byId/{idProducto}")
    public ResponseEntity<ProductoResponse> findById(@NotNull @PathVariable Long idProducto) {
        Producto producto= productoService.findById(idProducto);
        ProductoResponse response = mapper.productoToResponseProducto(producto);
        return ResponseEntity.ok(response);}
    }


