package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteRequest;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteProductoRequest;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteProductoResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

public interface ApiPaquete {

    ResponseEntity<PaqueteResponse> crearPaquete (@RequestBody PaqueteRequest paqueteRequest);

    void sumarProductoPaquete (@NotNull @PathVariable Long idPaquete, @RequestBody PaqueteProductoRequest paqueteProductoRequest) throws Exception;

    void sacarProductoPaquete(@NotNull @PathVariable Long idPaquete, @RequestBody PaqueteProductoRequest paqueteProductoRequest);

    void deletePaquete(@NotNull @PathVariable Long idPaquete);

    ResponseEntity<PaqueteProductoResponse>findPaquete (@PathVariable Long idPaquete);

}
