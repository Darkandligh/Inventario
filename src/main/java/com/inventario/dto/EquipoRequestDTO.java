package com.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EquipoRequestDTO {

    @NotBlank(message = "El tipo es obligatorio")
    @Size(max = 50, message = "El tipo no puede superar 50 caracteres")
    private String tipo;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede superar 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 100, message = "El modelo no puede superar 100 caracteres")
    private String modelo;

    @NotBlank(message = "El serial es obligatorio")
    @Size(max = 100, message = "El serial no puede superar 100 caracteres")
    private String serial;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede superar 50 caracteres")
    private String estado;

    @NotBlank(message = "El área es obligatoria")
    @Size(max = 100, message = "El área no puede superar 100 caracteres")
    private String area;

    @Size(max = 500, message = "Las observaciones no pueden superar 500 caracteres")
    private String observaciones;
}
