package com.inventario.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ModeloResponseDTO {

    private UUID id;
    private String nombre;
    private MarcaResumenDTO marca;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
