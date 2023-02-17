package com.stockweb.demo.config.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotPackage extends RuntimeException{

    private final Object resourceId;
}
