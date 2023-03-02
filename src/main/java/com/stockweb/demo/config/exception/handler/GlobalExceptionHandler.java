package com.stockweb.demo.config.exception.handler;

import com.stockweb.demo.config.exception.ConflictException;
import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotFoundException;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.config.exception.NotProductException;
import com.stockweb.demo.config.exception.error.ErrorCode;
import com.stockweb.demo.config.exception.error.ErrorDetails;
import com.stockweb.demo.config.exception.error.ErrorLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("No se encuentran datos con el recurso con id:  %s".formatted(ex.getResourceId()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<ErrorDetails> handleConflict(ConflictException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_ALREADY_EXISTS)
                .detail(ex.getConflictMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(NotPackage.class)
    private ResponseEntity<ErrorDetails> handleNotPackage(NotPackage ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .location(ErrorLocation.PATH)
                .detail("No se encontro el paquete con el id:  %s".formatted(ex.getResourceId()))
                .value("Id Paquete: "+ex.getResourceId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NotProductException.class)
    private ResponseEntity<ErrorDetails> handleNotProduct(NotProductException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .location(ErrorLocation.PATH)
                .detail("No se encontro el producto con el id:  %s".formatted(ex.getResourceId()))
                .value("Id Paquete: "+ex.getResourceId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ErrorExpected.class)
    private ResponseEntity<ErrorDetails> handleErrorExpected(ErrorExpected ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.EXPECTED_FAILURE)
                .detail(ex.getErrorMessage())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(error);
    }


}