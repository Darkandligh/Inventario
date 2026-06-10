package com.inventario.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TipoEquipoResumenDTO {

    private UUID id;
    private String nombre;
}
