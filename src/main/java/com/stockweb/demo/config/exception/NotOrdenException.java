package com.stockweb.demo.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotOrdenException extends RuntimeException{

    private final Object idOrden;
}
