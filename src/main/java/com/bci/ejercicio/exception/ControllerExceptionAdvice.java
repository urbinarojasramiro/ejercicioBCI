package com.bci.ejercicio.exception;

import com.bci.ejercicio.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler (RuntimeException ex){
        ErrorResponse error = ErrorResponse.builder().mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorResponse> requestExceptionHandler (RuntimeException ex){
        ErrorResponse error = ErrorResponse.builder().mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler (BusinessException ex){
        ErrorResponse error = ErrorResponse.builder().mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = UnexpectedException.class)
    public ResponseEntity<ErrorResponse> unexpectedExceptionHandler (UnexpectedException ex) {
        ErrorResponse error = ErrorResponse.builder().mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
