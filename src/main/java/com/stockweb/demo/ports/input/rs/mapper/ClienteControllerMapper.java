package com.stockweb.demo.ports.input.rs.mapper;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ClienteControllerMapper extends CommonMapper{

    @IterableMapping(qualifiedByName = "clienteToResponseCliente")
    List<ClienteResponse> clienteListaToClienteResponseList(List<Cliente> clientes);

    Cliente clienteRequestToCliente(ClienteRequest clienteRequest);

    @Named("clienteToResponseCliente")
    ClienteResponse clienteToResponseCliente(Cliente cliente);


}
