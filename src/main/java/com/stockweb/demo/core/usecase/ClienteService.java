package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.model.ClienteList;
import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import org.springframework.data.domain.PageRequest;

public interface ClienteService {

    Long createEntity(Cliente cliente);

    void deleteById(Long idCliente);

    void updateEntityIfExists(Long idCliente, ClienteRequest clienteRequest);

    ClienteList getLista(PageRequest pageRequest);

    Cliente findById(Long idCliente);



}
