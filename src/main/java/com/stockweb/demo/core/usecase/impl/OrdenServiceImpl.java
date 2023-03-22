package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotClientException;
import com.stockweb.demo.config.exception.NotOrdenException;
import com.stockweb.demo.config.exception.NotUserException;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.OrdenList;
import com.stockweb.demo.core.model.datetime.Fecha;
import com.stockweb.demo.core.repository.ClienteRepository;
import com.stockweb.demo.core.repository.EstadoOrdenRepository;
import com.stockweb.demo.core.repository.OrdenRepository;
import com.stockweb.demo.core.repository.UsuarioRepository;
import com.stockweb.demo.core.usecase.AuthService;
import com.stockweb.demo.core.usecase.OrdenService;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.request.orden.UpdateOrdenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;


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
                .precioTotal(0F)
                .usuario(authService.getUser())
                .adelanto(false)
                .build()
        );

    }

    @Override
    @Transactional
    public void deleteOrden(Long idOrden) {
        Orden orden = ordenRepository.findById(idOrden).orElseThrow(() -> new NotOrdenException(idOrden));
        orden.validEstadoGenerada("La orden no puede ser eliminada dado que no estsa en Estado Generada");
        ordenRepository.deleteById(idOrden);
    }
    @Override
    @Transactional
    public void updateEntityIfExists(Long idOrden, UpdateOrdenRequest ordenRequest) {

        ordenRepository.findById(idOrden).
                map(ordenJpa ->{
                    ordenJpa.validEstadoGenerada("La orden no se puede actualizar porque no esta en Estado Generada");
                    setOrden (ordenJpa, ordenRequest);
                    ordenJpa.setFechaModificacion(Fecha.get());
                    return ordenRepository.save(ordenJpa);
                });
    }
    @Override
    @Transactional
    public Orden findById(Long idOrden) {

        return ordenRepository.findById(idOrden).orElseThrow(() -> new NotOrdenException(idOrden) );
    }

    @Override
    @Transactional
    public void updateOrdenUser(Long idOrden, UpdateOrdenRequest updateOrdenRequest) {

        ordenRepository.findById(idOrden).
                map(ordenJpa ->{
                    ordenJpa.validEstadoGenerada("La orden no se puede actualizar porque no esta en Estado Generada");
                    ordenJpa.setUsuario(usuarioRepository.findById(updateOrdenRequest.getIdUser()).orElseThrow(()-> new NotUserException(updateOrdenRequest.getIdUser())));

                    ordenJpa.setFechaModificacion(Fecha.get());
                    return ordenRepository.save(ordenJpa);
                });


    }

    @Override
    @Transactional
    public OrdenList getLista(PageRequest pageRequest) {

        Page<Orden> page = ordenRepository.findAll(pageRequest);
        return new OrdenList(page.getContent(),pageRequest, page.getTotalElements());

    }

    private void setOrden(Orden ordenJpa, UpdateOrdenRequest updateOrdenRequest) {

        if (updateOrdenRequest.getIdCliente() != null)
            ordenJpa.setCliente(clienteRepository.findById(updateOrdenRequest.getIdCliente()).orElseThrow(()-> new NotClientException(updateOrdenRequest.getIdCliente())));

        if (updateOrdenRequest.getDescripcion() != null)
            ordenJpa.setDescripcion(updateOrdenRequest.getDescripcion());
    }


}
