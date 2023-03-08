package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.NotClientException;
import com.stockweb.demo.core.model.Cliente;
import com.stockweb.demo.core.model.ClienteList;
import com.stockweb.demo.core.repository.ClienteRepository;
import com.stockweb.demo.core.usecase.ClienteService;
import com.stockweb.demo.ports.input.rs.request.cliente.ClienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Long createEntity(Cliente cliente) {
        return clienteRepository.save(cliente).getIdCliente();
    }

    @Override
    @Transactional
    public void deleteById(Long idCliente) {
        clienteRepository.findById(idCliente).ifPresent(clienteRepository::delete);
    }

    @Override
    @Transactional
    public void updateEntityIfExists(Long idCliente, ClienteRequest clienteRequest) {

        clienteRepository.findById(idCliente).
                map(clienteJpa ->{
                    clienteJpa.setFirstname(clienteRequest.getFirstname());
                    clienteJpa.setLastname(clienteRequest.getLastname());
                    clienteJpa.setEmail(clienteRequest.getEmail());
                    return  clienteRepository.save(clienteJpa);
                }).orElseThrow(() -> new NotClientException(idCliente));

    }

    @Override
    @Transactional
    public ClienteList getLista(PageRequest pageRequest) {
        Page<Cliente> page = clienteRepository.findAll(pageRequest);
        return new ClienteList(page.getContent(),pageRequest, page.getTotalElements());
    }

    @Override
    @Transactional
    public Cliente findById(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(()-> new NotClientException(idCliente));
    }
}
