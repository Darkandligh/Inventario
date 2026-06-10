package com.inventario.controller;

import com.inventario.dto.AreaRequestDTO;
import com.inventario.dto.AreaResponseDTO;
import com.inventario.service.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<AreaResponseDTO> crearArea(@Valid @RequestBody AreaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.crearArea(request));
    }

    @GetMapping
    public ResponseEntity<List<AreaResponseDTO>> listarAreas() {
        return ResponseEntity.ok(areaService.listarAreas());
    }
}
