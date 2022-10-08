package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Product;
import com.stockweb.demo.ports.input.rs.request.ProductRequest;
import org.mapstruct.Mapper;


@Mapper
public interface ProductControllerMapper extends CommonMapper{

    Product productRequestToProduct(ProductRequest productRequest);
}
