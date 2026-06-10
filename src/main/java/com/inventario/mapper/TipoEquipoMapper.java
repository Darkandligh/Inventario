package com.inventario.mapper;

import com.inventario.dto.TipoEquipoRequestDTO;
import com.inventario.dto.TipoEquipoResponseDTO;
import com.inventario.dto.TipoEquipoResumenDTO;
import com.inventario.entity.TipoEquipo;
import org.springframework.stereotype.Component;

@Component
public class TipoEquipoMapper {

    public TipoEquipo toEntity(TipoEquipoRequestDTO dto) {
        return TipoEquipo.builder()
                .nombre(dto.getNombre())
                .build();
    }

    public TipoEquipoResponseDTO toResponseDTO(TipoEquipo tipoEquipo) {
        return TipoEquipoResponseDTO.builder()
                .id(tipoEquipo.getId())
                .nombre(tipoEquipo.getNombre())
                .createdAt(tipoEquipo.getCreatedAt())
                .updatedAt(tipoEquipo.getUpdatedAt())
                .build();
    }

    public TipoEquipoResumenDTO toResumenDTO(TipoEquipo tipoEquipo) {
        return TipoEquipoResumenDTO.builder()
                .id(tipoEquipo.getId())
                .nombre(tipoEquipo.getNombre())
                .build();
    }
}
