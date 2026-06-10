package com.inventario.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class EquipoResponseDTO {

    private UUID id;
    private String tipo;
    private String marca;
    private String modelo;
    private String serial;
    private String estado;
    private String area;
    private String observaciones;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
