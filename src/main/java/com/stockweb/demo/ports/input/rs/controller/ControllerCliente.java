package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.usecase.ClienteService;
import com.stockweb.demo.ports.input.rs.api.ApiCliente;
import com.stockweb.demo.ports.input.rs.mapper.ClienteControllerMapper;
import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponseLista;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.CLIENTE_URI;

@RestController
@RequestMapping(CLIENTE_URI)
@RequiredArgsConstructor
public class ControllerCliente implements ApiCliente {

    private final ClienteControllerMapper mapper;

    private final ClienteService clienteService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = mapper.clienteRequestToCliente(clienteRequest);
        final long idCliente = clienteService.createEntity(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/byId/{id}").buildAndExpand(idCliente)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public void upDateCliente(Long id, ClienteRequest clienteRequest) {

    }

    @Override
    public void DeleteCliente(Long idCliente) {

    }

    @Override
    public ResponseEntity<ClienteResponseLista> getAllClientes(Optional<Integer> page, Optional<Integer> size) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteResponse> findById(Long idCliente) {
        return null;
    }
}
