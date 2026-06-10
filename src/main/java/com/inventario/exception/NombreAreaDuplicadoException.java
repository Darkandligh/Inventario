package com.inventario.exception;

public class NombreAreaDuplicadoException extends RuntimeException {

    public NombreAreaDuplicadoException(String nombre) {
        super("Ya existe un área con el nombre: " + nombre);
    }
}
