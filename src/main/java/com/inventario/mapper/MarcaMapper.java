package com.inventario.mapper;

import com.inventario.dto.MarcaRequestDTO;
import com.inventario.dto.MarcaResponseDTO;
import com.inventario.dto.MarcaResumenDTO;
import com.inventario.entity.Marca;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {

    public Marca toEntity(MarcaRequestDTO dto) {
        return Marca.builder()
                .nombre(dto.getNombre())
                .build();
    }

    public MarcaResponseDTO toResponseDTO(Marca marca) {
        return MarcaResponseDTO.builder()
                .id(marca.getId())
                .nombre(marca.getNombre())
                .createdAt(marca.getCreatedAt())
                .updatedAt(marca.getUpdatedAt())
                .build();
    }

    public MarcaResumenDTO toResumenDTO(Marca marca) {
        return MarcaResumenDTO.builder()
                .id(marca.getId())
                .nombre(marca.getNombre())
                .build();
    }
}
