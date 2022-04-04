package com.bci.ejercicio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse
{
    private String mensaje;
}
