package com.inventario.service;

import com.inventario.dto.ModeloRequestDTO;
import com.inventario.dto.ModeloResponseDTO;
import com.inventario.mapper.ModeloMapper;
import com.inventario.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final ModeloMapper modeloMapper;

    @Transactional
    public ModeloResponseDTO crearModelo(ModeloRequestDTO request) {
        return modeloMapper.toResponseDTO(
                modeloRepository.save(modeloMapper.toEntity(request))
        );
    }

    @Transactional(readOnly = true)
    public List<ModeloResponseDTO> listarModelos() {
        return modeloRepository.findAll()
                .stream()
                .map(modeloMapper::toResponseDTO)
                .toList();
    }
}
