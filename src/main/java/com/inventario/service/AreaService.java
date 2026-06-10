package com.inventario.service;

import com.inventario.dto.AreaRequestDTO;
import com.inventario.dto.AreaResponseDTO;
import com.inventario.exception.NombreAreaDuplicadoException;
import com.inventario.mapper.AreaMapper;
import com.inventario.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    @Transactional
    public AreaResponseDTO crearArea(AreaRequestDTO request) {
        if (areaRepository.existsByNombre(request.getNombre())) {
            throw new NombreAreaDuplicadoException(request.getNombre());
        }

        return areaMapper.toResponseDTO(
                areaRepository.save(areaMapper.toEntity(request))
        );
    }

    @Transactional(readOnly = true)
    public List<AreaResponseDTO> listarAreas() {
        return areaRepository.findAll()
                .stream()
                .map(areaMapper::toResponseDTO)
                .toList();
    }
}
