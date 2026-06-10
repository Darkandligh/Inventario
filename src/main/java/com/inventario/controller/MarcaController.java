package com.inventario.controller;

import com.inventario.dto.MarcaRequestDTO;
import com.inventario.dto.MarcaResponseDTO;
import com.inventario.service.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> crearMarca(@Valid @RequestBody MarcaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.crearMarca(request));
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listarMarcas() {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }
}
