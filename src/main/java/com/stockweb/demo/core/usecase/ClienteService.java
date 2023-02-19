package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.model.ClienteList;
import com.stockweb.demo.core.model.Producto;
import org.springframework.data.domain.PageRequest;

public interface ClienteService {

    Long createEntity(Cliente cliente);

    void deleteById(Long idCliente);

    void updateEntityIfExists(Long idCliente, Cliente cliente);

    ClienteList getLista(PageRequest pageRequest);

    Cliente findById(Long idCliente);



}
