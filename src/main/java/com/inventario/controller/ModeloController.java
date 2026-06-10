package com.inventario.controller;

import com.inventario.dto.ModeloRequestDTO;
import com.inventario.dto.ModeloResponseDTO;
import com.inventario.service.ModeloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
@RequiredArgsConstructor
public class ModeloController {

    private final ModeloService modeloService;

    @PostMapping
    public ResponseEntity<ModeloResponseDTO> crearModelo(@Valid @RequestBody ModeloRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloService.crearModelo(request));
    }

    @GetMapping
    public ResponseEntity<List<ModeloResponseDTO>> listarModelos() {
        return ResponseEntity.ok(modeloService.listarModelos());
    }
}
