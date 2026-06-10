package com.inventario.dto.sync;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EquipoPushDTO {

    @NotNull(message = "El id es obligatorio")
    private UUID id;

    @NotNull(message = "El updatedAt es obligatorio")
    private LocalDateTime updatedAt;

    @NotNull(message = "El tipo es obligatorio")
    private UUID tipoId;

    @NotNull(message = "La marca es obligatoria")
    private UUID marcaId;

    @NotNull(message = "El modelo es obligatorio")
    private UUID modeloId;

    @NotBlank(message = "El serial es obligatorio")
    @Size(max = 100)
    private String serial;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50)
    private String estado;

    @NotNull(message = "El área es obligatoria")
    private UUID areaId;

    @Size(max = 500)
    private String observaciones;
}
