package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.ports.input.rs.request.ProductoRequest;
import com.stockweb.demo.ports.input.rs.response.ProductoResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;


@Mapper
public interface ProductoControllerMapper extends CommonMapper{

    @IterableMapping(qualifiedByName = "productoToResponseProducto")
    List<ProductoResponse> productoListaToProductoResponseList(List<Producto> productos);

    Producto productoRequestToProducto(ProductoRequest productoRequest);

    @Named("productoToResponseProducto")
    ProductoResponse productoToResponseProducto(Producto propducto);

}
