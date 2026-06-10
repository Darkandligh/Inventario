package com.inventario.exception;

import java.util.UUID;

public class ModeloNotFoundException extends RuntimeException {

    public ModeloNotFoundException(UUID id) {
        super("No existe un modelo con el id: " + id);
    }
}
