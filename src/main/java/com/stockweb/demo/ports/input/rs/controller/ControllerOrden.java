package com.stockweb.demo.ports.input.rs.controller;

import com.stockweb.demo.core.model.ClienteList;
import com.stockweb.demo.core.model.Orden;
import com.stockweb.demo.core.model.OrdenList;
import com.stockweb.demo.core.usecase.OrdenService;
import com.stockweb.demo.ports.input.rs.api.ApiConstants;
import com.stockweb.demo.ports.input.rs.api.ApiOrden;
import com.stockweb.demo.ports.input.rs.mapper.OrdenControllerMapper;
import com.stockweb.demo.ports.input.rs.request.orden.OrdenRequest;
import com.stockweb.demo.ports.input.rs.request.orden.UpdateOrdenRequest;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponseLista;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenAllResponse;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenAllResponseLista;
import com.stockweb.demo.ports.input.rs.response.orden.OrdenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.ORDEN_URI;


@RestController
@RequestMapping(ORDEN_URI)
@RequiredArgsConstructor
public class ControllerOrden implements ApiOrden {

    private final OrdenService ordenService;

    private final OrdenControllerMapper ordenControllerMapper;


    @Override
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<OrdenResponse> createOrden(@Valid @RequestBody OrdenRequest ordenRequest) {

        Orden orden = ordenService.createOrden(ordenRequest);
        OrdenResponse ordenResponse = OrdenResponse.builder()
                .nombreUsuario(orden.getUsuario().getFirstname() + " " + orden.getUsuario().getLastname())
                .claveEstado(orden.getEstadoOrden().getClaveEstado())
                .nombreCliente(orden.getCliente().getFirstname() + " " + orden.getCliente().getLastname())
                .fechaGenerada(orden.getFechaGenerada()).build();

        return ResponseEntity.ok().body(ordenResponse);
    }
    @Override
    @DeleteMapping ("/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public void deleteOrden(Long idOrden) {
        ordenService.deleteOrden(idOrden);
    }

    @Override
    @PatchMapping ("editOrden/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Void> uptadeOrden(@NotNull @PathVariable Long idOrden, @Valid @RequestBody UpdateOrdenRequest updateOrdenRequest) {

        //este ordenRequest sera solo para modificar al cliente y/o descripcion

        ordenService.updateEntityIfExists (idOrden, updateOrdenRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiConstants.ORDEN_URI + "/byId/{idOrden}")
                .buildAndExpand(idOrden)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @PatchMapping ("editUser/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Void> uptadeOrdenAdmin(@NotNull @PathVariable Long idOrden, @Valid @RequestBody UpdateOrdenRequest updateOrdenRequest) {

        //este ordenRequest sera solo para modificar al usuario que creo la orden (solo admin puede tocar esto)

        ordenService.updateOrdenUser (idOrden, updateOrdenRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiConstants.ORDEN_URI + "/byId/{idOrden}")
                .buildAndExpand(idOrden)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping ("/byId/{idOrden}")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<OrdenAllResponse> findOrden(@NotNull @PathVariable Long idOrden) {

        Orden orden = ordenService.findById(idOrden);
        OrdenAllResponse ordenAllResponse = ordenControllerMapper.ordenToResponseOrden(orden);
        return ResponseEntity.ok().body(ordenAllResponse);

    }

    @Override
    @GetMapping 
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<OrdenAllResponseLista> getAllOrdenes(Optional<Integer> page, Optional<Integer> size) {



        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        OrdenList list = ordenService.getLista(PageRequest.of(pageNumber,pageSize));

        OrdenAllResponseLista response;
        {
            response = new OrdenAllResponseLista();
            List<OrdenAllResponse> contenido =  ordenControllerMapper.ordenListaToOrdenResponseLista(list.getContent());
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


}
