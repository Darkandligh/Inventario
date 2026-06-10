package com.inventario.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AreaResponseDTO {

    private UUID id;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
