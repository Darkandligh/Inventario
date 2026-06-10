package com.inventario.controller;

import com.inventario.dto.EquipoRequestDTO;
import com.inventario.dto.EquipoResponseDTO;
import com.inventario.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crearEquipo(@Valid @RequestBody EquipoRequestDTO request) {
        EquipoResponseDTO response = equipoService.crearEquipo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EquipoResponseDTO>> listarEquipos() {
        return ResponseEntity.ok(equipoService.listarEquipos());
    }
}
