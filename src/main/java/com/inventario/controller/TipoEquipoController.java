package com.inventario.controller;

import com.inventario.dto.TipoEquipoRequestDTO;
import com.inventario.dto.TipoEquipoResponseDTO;
import com.inventario.service.TipoEquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@RequiredArgsConstructor
public class TipoEquipoController {

    private final TipoEquipoService tipoEquipoService;

    @PostMapping
    public ResponseEntity<TipoEquipoResponseDTO> crearTipoEquipo(@Valid @RequestBody TipoEquipoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEquipoService.crearTipoEquipo(request));
    }

    @GetMapping
    public ResponseEntity<List<TipoEquipoResponseDTO>> listarTiposEquipo() {
        return ResponseEntity.ok(tipoEquipoService.listarTiposEquipo());
    }
}
