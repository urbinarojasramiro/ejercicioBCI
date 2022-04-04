package com.bci.ejercicio.exception;

import lombok.Data;

@Data
public class UnexpectedException extends RuntimeException{

    public UnexpectedException(String message) {
        super(message);
    }
}
