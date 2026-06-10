package com.inventario.mapper;

import com.inventario.dto.EquipoRequestDTO;
import com.inventario.dto.EquipoResponseDTO;
import com.inventario.entity.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public Equipo toEntity(EquipoRequestDTO dto) {
        return Equipo.builder()
                .tipo(dto.getTipo())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .serial(dto.getSerial())
                .estado(dto.getEstado())
                .area(dto.getArea())
                .observaciones(dto.getObservaciones())
                .build();
    }

    public EquipoResponseDTO toResponseDTO(Equipo equipo) {
        return EquipoResponseDTO.builder()
                .id(equipo.getId())
                .tipo(equipo.getTipo())
                .marca(equipo.getMarca())
                .modelo(equipo.getModelo())
                .serial(equipo.getSerial())
                .estado(equipo.getEstado())
                .area(equipo.getArea())
                .observaciones(equipo.getObservaciones())
                .createdAt(equipo.getCreatedAt())
                .updatedAt(equipo.getUpdatedAt())
                .build();
    }
}
