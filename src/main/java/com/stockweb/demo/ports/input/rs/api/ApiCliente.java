package com.stockweb.demo.ports.input.rs.api;

import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponseLista;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface ApiCliente {
    ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteRequest ClienteRequest);

    void upDateCliente(@NotNull @PathVariable Long idCliente, @Valid @RequestBody ClienteRequest clienteRequest);

    void DeleteCliente(@NotNull @PathVariable Long idCliente);

    ResponseEntity<ClienteResponseLista> getAllClientes(@RequestParam Optional<Integer> page,
                                                        @RequestParam Optional<Integer> size);

    ResponseEntity<ClienteResponse> findById(@NotNull @PathVariable Long idCliente);
}




