package com.stockweb.demo.ports.input.rs.api;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.function.Function;

public interface ApiConstants {


    String PRODUCT_URI = "/productos";

    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = (page) ->
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .replaceQueryParam("page", page).toUriString();

}

