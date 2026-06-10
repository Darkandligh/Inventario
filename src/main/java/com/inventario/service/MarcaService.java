package com.inventario.service;

import com.inventario.dto.MarcaRequestDTO;
import com.inventario.dto.MarcaResponseDTO;
import com.inventario.exception.NombreMarcaDuplicadoException;
import com.inventario.mapper.MarcaMapper;
import com.inventario.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Transactional
    public MarcaResponseDTO crearMarca(MarcaRequestDTO request) {
        if (marcaRepository.existsByNombre(request.getNombre())) {
            throw new NombreMarcaDuplicadoException(request.getNombre());
        }

        return marcaMapper.toResponseDTO(
                marcaRepository.save(marcaMapper.toEntity(request))
        );
    }

    @Transactional(readOnly = true)
    public List<MarcaResponseDTO> listarMarcas() {
        return marcaRepository.findAll()
                .stream()
                .map(marcaMapper::toResponseDTO)
                .toList();
    }
}
