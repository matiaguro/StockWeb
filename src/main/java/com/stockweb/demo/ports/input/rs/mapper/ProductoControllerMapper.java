package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import com.stockweb.demo.ports.input.rs.response.ProductoResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface ProductControllerMapper extends CommonMapper{

    Producto productoRequestToProduct(ProductRequest productRequest);

    ProductoResponse productoToProductResponse(List<Producto> productRequest);
}
