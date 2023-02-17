package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteRequest;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteProductoResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteResponse;
import org.mapstruct.Mapper;



@Mapper
public interface PaqueteControllerMapper extends CommonMapper {

   PaqueteResponse paqueteToPaqueteResponse (Paquete paquete);
   Paquete paqueteRequestToPaquete (PaqueteRequest paqueteRequest);

   PaqueteProductoResponse paqueteToPaqueteProductoResponse(Paquete paquete);
}
