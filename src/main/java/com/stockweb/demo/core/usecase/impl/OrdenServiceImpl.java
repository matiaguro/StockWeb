package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotClientException;
import com.stockweb.demo.config.exception.NotUserException;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.datetime.Fecha;
import com.stockweb.demo.core.repository.ClienteRepository;
import com.stockweb.demo.core.repository.EstadoOrdenRepository;
import com.stockweb.demo.core.repository.OrdenRepository;
import com.stockweb.demo.core.repository.UsuarioRepository;
import com.stockweb.demo.core.usecase.AuthService;
import com.stockweb.demo.core.usecase.OrdenService;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final ClienteRepository clienteRepository;

    private final UsuarioRepository usuarioRepository;

    private final OrdenRepository ordenRepository;

    private final EstadoOrdenRepository estadoOrdenRepository;

    private final AuthService authService;


    @Override
    @Transactional
    public Orden createOrden(OrdenRequest ordenRequest) {

        return ordenRepository.save(
                Orden.builder()
                .estadoOrden(estadoOrdenRepository.findById(1L).orElseThrow(() -> new ErrorExpected("No se encontro el estado orden", HttpStatus.NO_CONTENT)))
                .cliente(clienteRepository.findById(ordenRequest.getIdCliente()).orElseThrow(()-> new NotClientException(ordenRequest.getIdCliente())))
                .fechaGenerada(Fecha.get())
                .fechaModificacion(Fecha.get())
                .precioTotal(0)
                .usuario(authService.getUser())
                .build()
        );

    }

    @Override
    @Transactional
    public Void deleteOrden(Long idOrden) {
        ordenRepository.deleteById(idOrden);
        return null;
    }

    @Override
    @Transactional
    public Orden updateEntityIfExists(Long idOrden, Long idUser) {

        ordenRepository.findById(idOrden).
                map(ordenJpa ->{
                    ordenJpa.setUsuario(usuarioRepository.findById(idUser).orElseThrow(()-> new NotUserException(idUser)));
                    return ordenRepository.save(ordenJpa);
                });


        return null;
    }

    @Override
    public void updateFechaUltimaModificacion(Long idOrden) {

        ordenRepository.findById(idOrden)
                .map(fechaJpa -> {
                     fechaJpa.setFechaModificacion(Fecha.get());
                     return ordenRepository.save(fechaJpa);

        } );

    }


}
