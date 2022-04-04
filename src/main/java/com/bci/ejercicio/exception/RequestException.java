package com.bci.ejercicio.exception;

import lombok.Data;

@Data
public class RequestException extends RuntimeException{

    public RequestException(String message) {
        super(message);
    }
}
