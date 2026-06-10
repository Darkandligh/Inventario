package com.inventario.mapper;

import com.inventario.dto.AreaRequestDTO;
import com.inventario.dto.AreaResponseDTO;
import com.inventario.dto.AreaResumenDTO;
import com.inventario.entity.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    public Area toEntity(AreaRequestDTO dto) {
        return Area.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    public AreaResponseDTO toResponseDTO(Area area) {
        return AreaResponseDTO.builder()
                .id(area.getId())
                .nombre(area.getNombre())
                .descripcion(area.getDescripcion())
                .createdAt(area.getCreatedAt())
                .updatedAt(area.getUpdatedAt())
                .build();
    }

    public AreaResumenDTO toResumenDTO(Area area) {
        return AreaResumenDTO.builder()
                .id(area.getId())
                .nombre(area.getNombre())
                .build();
    }
}
