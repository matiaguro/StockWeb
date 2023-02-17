package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.core.usecase.PaqueteService;
import com.stockweb.demo.ports.input.rs.api.ApiPaquete;
import com.stockweb.demo.ports.input.rs.mapper.PaqueteControllerMapper;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteProductoRequest;
import com.stockweb.demo.ports.input.rs.request.paquete.PaqueteRequest;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteProductoResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.PAQUETE_URI;

@RestController
@RequestMapping(PAQUETE_URI)
@RequiredArgsConstructor
public class PaqueteController implements ApiPaquete {

    @Value("${app.default.name-paquete}")
    private String defaultName;
    private final PaqueteControllerMapper mapper;

    private final PaqueteService paqueteService;

    @Override
    @PostMapping()
    public ResponseEntity<PaqueteResponse> crearPaquete( @RequestBody PaqueteRequest paqueteRequest) {
        Paquete paquete = mapper.paqueteRequestToPaquete(paqueteRequest);
        if (paquete.getNombrePaquete() == null || paquete.getNombrePaquete().isEmpty()){paquete.setNombrePaquete(defaultName);}
        PaqueteResponse paqueteResponse = mapper.paqueteToPaqueteResponse(paqueteService.crearPaquete(paquete));
        return ResponseEntity.ok().body(paqueteResponse);
    }

    @Override
    @PatchMapping("/agregarProductos/{idPaquete}")
    @ResponseStatus(HttpStatus.OK)
    public void sumarProductoPaquete(@NotNull  @PathVariable Long idPaquete, @RequestBody PaqueteProductoRequest paqueteProductoRequest) throws Exception {
        paqueteService.agregarProducto(idPaquete ,paqueteProductoRequest);
    }

    @Override
    @PatchMapping("/sacarProductos/{idPaquete}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sacarProductoPaquete(@NotNull  @PathVariable Long idPaquete, @RequestBody PaqueteProductoRequest paqueteProductoRequest) {
        paqueteService.sacarProducto(idPaquete ,paqueteProductoRequest);
    }

    @Override
    @DeleteMapping("/{idPaquete}")
    public void deletePaquete(@PathVariable  Long idPaquete) {
        paqueteService.deletePaquete(idPaquete);
    }

    @Override
    @GetMapping("findPaquete/{idPaquete}")
    public ResponseEntity<PaqueteProductoResponse> findPaquete(Long idPaquete) {
        Paquete paquete = paqueteService.findByid(idPaquete);
        PaqueteProductoResponse response = mapper.paqueteToPaqueteProductoResponse(paquete);
        return ResponseEntity.ok().body(response);
    }
}
