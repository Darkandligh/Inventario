package com.inventario.service;

import com.inventario.dto.EquipoRequestDTO;
import com.inventario.dto.EquipoResponseDTO;
import com.inventario.entity.Equipo;
import com.inventario.exception.SerialDuplicadoException;
import com.inventario.mapper.EquipoMapper;
import com.inventario.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    @Transactional
    public EquipoResponseDTO crearEquipo(EquipoRequestDTO request) {
        if (equipoRepository.existsBySerial(request.getSerial())) {
            throw new SerialDuplicadoException("Ya existe un equipo con el serial: " + request.getSerial());
        }

        Equipo equipo = equipoMapper.toEntity(request);
        Equipo guardado = equipoRepository.save(equipo);
        return equipoMapper.toResponseDTO(guardado);
    }

    @Transactional(readOnly = true)
    public List<EquipoResponseDTO> listarEquipos() {
        return equipoRepository.findAll()
                .stream()
                .map(equipoMapper::toResponseDTO)
                .toList();
    }
}
