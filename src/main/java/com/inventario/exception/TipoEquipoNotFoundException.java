package com.inventario.exception;

import java.util.UUID;

public class TipoEquipoNotFoundException extends RuntimeException {

    public TipoEquipoNotFoundException(UUID id) {
        super("No existe un tipo de equipo con el id: " + id);
    }
}
