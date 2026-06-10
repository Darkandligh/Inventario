package com.inventario.service;

import com.inventario.dto.TipoEquipoRequestDTO;
import com.inventario.dto.TipoEquipoResponseDTO;
import com.inventario.exception.NombreTipoEquipoDuplicadoException;
import com.inventario.mapper.TipoEquipoMapper;
import com.inventario.repository.TipoEquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEquipoService {

    private final TipoEquipoRepository tipoEquipoRepository;
    private final TipoEquipoMapper tipoEquipoMapper;

    @Transactional
    public TipoEquipoResponseDTO crearTipoEquipo(TipoEquipoRequestDTO request) {
        if (tipoEquipoRepository.existsByNombre(request.getNombre())) {
            throw new NombreTipoEquipoDuplicadoException(request.getNombre());
        }

        return tipoEquipoMapper.toResponseDTO(
                tipoEquipoRepository.save(tipoEquipoMapper.toEntity(request))
        );
    }

    @Transactional(readOnly = true)
    public List<TipoEquipoResponseDTO> listarTiposEquipo() {
        return tipoEquipoRepository.findAll()
                .stream()
                .map(tipoEquipoMapper::toResponseDTO)
                .toList();
    }
}
