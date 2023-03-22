package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenAllResponse;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface OrdenControllerMapper extends CommonMapper{

    @Named("ordenToResponseOrden")
    OrdenAllResponse ordenToResponseOrden (Orden orden);

    @IterableMapping (qualifiedByName = "ordenToResponseOrden")
    List<OrdenAllResponse> ordenListaToOrdenResponseLista (List<Orden> ordenes);


}
