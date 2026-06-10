package com.inventario.exception;

import java.util.UUID;

public class AreaNotFoundException extends RuntimeException {

    public AreaNotFoundException(UUID id) {
        super("No existe un área con el id: " + id);
    }
}
