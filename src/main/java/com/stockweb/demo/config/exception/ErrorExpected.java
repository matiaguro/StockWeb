package com.stockweb.demo.config.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorExpected extends  RuntimeException{

    private final String errorMessage;

    private final HttpStatus status;
}
