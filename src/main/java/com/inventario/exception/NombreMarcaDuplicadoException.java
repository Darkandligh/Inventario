package com.inventario.exception;

public class NombreMarcaDuplicadoException extends RuntimeException {

    public NombreMarcaDuplicadoException(String nombre) {
        super("Ya existe una marca con el nombre: " + nombre);
    }
}
