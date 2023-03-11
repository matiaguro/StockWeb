package com.stockweb.demo.ports.input.rs.api;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.function.Function;

public interface ApiConstants {

    String CLIENTE_URI = "/cliente";
    String ORDEN_URI = "/orden";
    String AUTH_URI= "/auth";
    String PRODUCT_URI = "/productos";
    String USUARIOS_URI ="/usuarios";
    String PAQUETE_URI = "/paquete";

    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = (page) ->
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .replaceQueryParam("page", page).toUriString();

}

