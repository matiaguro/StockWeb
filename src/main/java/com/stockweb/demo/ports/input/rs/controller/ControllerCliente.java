package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.model.ClienteList;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.usecase.ClienteService;
import com.stockweb.demo.ports.input.rs.api.ApiCliente;
import com.stockweb.demo.ports.input.rs.api.ApiConstants;
import com.stockweb.demo.ports.input.rs.mapper.ClienteControllerMapper;
import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponseLista;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
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
    @PatchMapping("editCliente/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public void upDateCliente(@NotNull @PathVariable Long idCliente, @Valid @RequestBody  ClienteRequest clienteRequest) {
        clienteService.updateEntityIfExists(idCliente, clienteRequest);

    }

    @Override
    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteCliente(@NotNull @PathVariable Long idCliente){
        clienteService.deleteById(idCliente);
    }

    @Override
    @GetMapping("/allClientes")
    public ResponseEntity<ClienteResponseLista> getAllClientes( @RequestParam Optional<Integer> page,
                                                                @RequestParam Optional<Integer> size) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        ClienteList list = clienteService.getLista(PageRequest.of(pageNumber,pageSize));

        ClienteResponseLista response;
        {
            response = new ClienteResponseLista();
            List<ClienteResponse> contenido = mapper.clienteListaToClienteResponseList(list.getContent());
            response.setContent (contenido);
            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage =list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());


        }

        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/byId/{idCliente}")
    public ResponseEntity<ClienteResponse> findById(@NotNull @PathVariable Long idCliente) {
        Cliente cliente = clienteService.findById(idCliente);
        ClienteResponse response = mapper.clienteToResponseCliente(cliente);
        return ResponseEntity.ok(response);
    }
}
