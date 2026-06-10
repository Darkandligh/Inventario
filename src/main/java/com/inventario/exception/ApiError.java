package com.inventario.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiError {

    private int status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;
}
