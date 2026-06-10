package com.inventario.exception;

import java.util.UUID;

public class MarcaNotFoundException extends RuntimeException {

    public MarcaNotFoundException(UUID id) {
        super("No existe una marca con el id: " + id);
    }
}
