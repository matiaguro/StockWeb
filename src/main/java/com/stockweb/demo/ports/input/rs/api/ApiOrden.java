package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

public interface ApiOrden {


    ResponseEntity<OrdenResponse> createOrden(@Valid @RequestBody OrdenRequest ordenRequest);


}
