package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.request.orden.UpdateOrdenRequest;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ApiOrden {

    ResponseEntity<OrdenResponse> createOrden(@Valid @RequestBody OrdenRequest ordenRequest);

    void deleteOrden (@NotNull @PathVariable Long idOrden);

    ResponseEntity<Void> uptadeOrdenAdmin (@NotNull @PathVariable Long idOrden,
                                           @Valid @RequestBody UpdateOrdenRequest updateOrdenRequest);


    ResponseEntity <OrdenResponse> updateOrdenUser (@Valid @RequestBody Long idOrden,
                                                    @Valid @RequestBody Optional <Cliente> cliente,
                                                    @Valid @RequestBody Optional <String> descripcion);

}
