package com.inventario.mapper;

import com.inventario.dto.EquipoRequestDTO;
import com.inventario.dto.EquipoResponseDTO;
import com.inventario.entity.Area;
import com.inventario.entity.Equipo;
import com.inventario.entity.Marca;
import com.inventario.entity.Modelo;
import com.inventario.entity.TipoEquipo;
import com.inventario.exception.AreaNotFoundException;
import com.inventario.exception.MarcaNotFoundException;
import com.inventario.exception.ModeloNotFoundException;
import com.inventario.exception.TipoEquipoNotFoundException;
import com.inventario.repository.AreaRepository;
import com.inventario.repository.MarcaRepository;
import com.inventario.repository.ModeloRepository;
import com.inventario.repository.TipoEquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipoMapper {

    private final TipoEquipoRepository tipoEquipoRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final AreaRepository areaRepository;

    private final TipoEquipoMapper tipoEquipoMapper;
    private final MarcaMapper marcaMapper;
    private final ModeloMapper modeloMapper;
    private final AreaMapper areaMapper;

    public Equipo toEntity(EquipoRequestDTO dto) {
        TipoEquipo tipo = tipoEquipoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new TipoEquipoNotFoundException(dto.getTipoId()));

        Marca marca = marcaRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(dto.getMarcaId()));

        Modelo modelo = modeloRepository.findById(dto.getModeloId())
                .orElseThrow(() -> new ModeloNotFoundException(dto.getModeloId()));

        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new AreaNotFoundException(dto.getAreaId()));

        return Equipo.builder()
                .tipo(tipo)
                .marca(marca)
                .modelo(modelo)
                .serial(dto.getSerial())
                .estado(dto.getEstado())
                .area(area)
                .observaciones(dto.getObservaciones())
                .build();
    }

    public EquipoResponseDTO toResponseDTO(Equipo equipo) {
        return EquipoResponseDTO.builder()
                .id(equipo.getId())
                .tipo(tipoEquipoMapper.toResumenDTO(equipo.getTipo()))
                .marca(marcaMapper.toResumenDTO(equipo.getMarca()))
                .modelo(modeloMapper.toResumenDTO(equipo.getModelo()))
                .serial(equipo.getSerial())
                .estado(equipo.getEstado())
                .area(areaMapper.toResumenDTO(equipo.getArea()))
                .observaciones(equipo.getObservaciones())
                .createdAt(equipo.getCreatedAt())
                .updatedAt(equipo.getUpdatedAt())
                .build();
    }
}
