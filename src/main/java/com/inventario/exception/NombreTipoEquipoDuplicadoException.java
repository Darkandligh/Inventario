package com.inventario.exception;

public class NombreTipoEquipoDuplicadoException extends RuntimeException {

    public NombreTipoEquipoDuplicadoException(String nombre) {
        super("Ya existe un tipo de equipo con el nombre: " + nombre);
    }
}
