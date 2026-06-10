package com.inventario.mapper;

import com.inventario.dto.ModeloRequestDTO;
import com.inventario.dto.ModeloResponseDTO;
import com.inventario.dto.ModeloResumenDTO;
import com.inventario.entity.Marca;
import com.inventario.entity.Modelo;
import com.inventario.exception.MarcaNotFoundException;
import com.inventario.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModeloMapper {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public Modelo toEntity(ModeloRequestDTO dto) {
        Marca marca = marcaRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(dto.getMarcaId()));

        return Modelo.builder()
                .nombre(dto.getNombre())
                .marca(marca)
                .build();
    }

    public ModeloResponseDTO toResponseDTO(Modelo modelo) {
        return ModeloResponseDTO.builder()
                .id(modelo.getId())
                .nombre(modelo.getNombre())
                .marca(marcaMapper.toResumenDTO(modelo.getMarca()))
                .createdAt(modelo.getCreatedAt())
                .updatedAt(modelo.getUpdatedAt())
                .build();
    }

    public ModeloResumenDTO toResumenDTO(Modelo modelo) {
        return ModeloResumenDTO.builder()
                .id(modelo.getId())
                .nombre(modelo.getNombre())
                .build();
    }
}
