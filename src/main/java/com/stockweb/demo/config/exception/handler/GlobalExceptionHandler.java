package com.stockweb.demo.config.exception.handler;

import com.stockweb.demo.config.exception.ConflictException;
import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotClientException;
import com.stockweb.demo.config.exception.NotFoundException;
import com.stockweb.demo.config.exception.NotOrdenException;
import com.stockweb.demo.config.exception.NotPackage;
import com.stockweb.demo.config.exception.NotProductException;
import com.stockweb.demo.config.exception.NotUserException;
import com.stockweb.demo.config.exception.error.ErrorCode;
import com.stockweb.demo.config.exception.error.ErrorConstraint;
import com.stockweb.demo.config.exception.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


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
                .detail("No se encontro el paquete con el id:  %s".formatted(ex.getResourceId()))
                .value("Id Paquete: "+ex.getResourceId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(NotUserException.class)
    private ResponseEntity<ErrorDetails> handleNotUser(NotUserException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("No se encontro el usuario con el email:  %s".formatted(ex.getResourceId()))
                .value("Id Paquete: "+ex.getResourceId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(NotProductException.class)
    private ResponseEntity<ErrorDetails> handleNotProduct(NotProductException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
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
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ResponseEntity<ErrorConstraint> handleErrorConstrain(ConstraintViolationException ex) {
        ErrorConstraint errores = new ErrorConstraint();
        for (ConstraintViolation violation : ex.getConstraintViolations()){
            ErrorDetails error = ErrorDetails.builder()
                    .code(ErrorCode.EXPECTED_FAILURE)
                    .detail(violation.getMessage())
                    .build();
            errores.getErrores().add(error);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ResponseEntity<ErrorDetails> handleErrorArgumentConstrain(MethodArgumentNotValidException ex) {
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            ErrorDetails error = ErrorDetails.builder()
                    .code(ErrorCode.EXPECTED_FAILURE)
                    .detail(fieldError.getDefaultMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        return null;
    }


    @ExceptionHandler(NotClientException.class)
    private  ResponseEntity<ErrorDetails> handleNotClient (NotClientException ex){

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("No se encontro el cliente con el id: %s".formatted(ex.getResourceId()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NotOrdenException.class)
    private  ResponseEntity<ErrorDetails> handleNotOrden (NotOrdenException ex){

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("No se encontro la orden con el id: %s".formatted(ex.getIdOrden()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}