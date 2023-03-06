package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.DescPaquete;
import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.ports.input.rs.request.paquete.DescPaqueteResponse;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteRequest;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteProductoResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper
public interface PaqueteControllerMapper extends CommonMapper {

   PaqueteResponse paqueteToPaqueteResponse (Paquete paquete);

   Paquete paqueteRequestToPaquete (PaqueteRequest paqueteRequest);

   @Mapping(target="productos", source="descPaqueteList")
   PaqueteProductoResponse paqueteToPaqueteProductoResponse(Paquete paquete);

   @Named("descPaqueteToDescPaqueteResponseList")
   List<DescPaqueteResponse> descPaqueteToDescPaqueteResponseList (List<DescPaquete> descPaqueteList);

   @Named("descPaqueteToDescPaqueteResponse")
   DescPaqueteResponse descPaqueteToDescPaqueteResponse (DescPaquete descPaquete);



}
