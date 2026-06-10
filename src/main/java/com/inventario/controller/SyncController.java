package com.inventario.controller;

import com.inventario.dto.sync.SyncPushResponseDTO;
import com.inventario.dto.sync.SyncRequestDTO;
import com.inventario.dto.sync.SyncResponseDTO;
import com.inventario.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final SyncService syncService;

    @GetMapping("/pull")
    public ResponseEntity<SyncResponseDTO> pull(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastPulledAt) {
        return ResponseEntity.ok(syncService.pull(lastPulledAt));
    }

    @PostMapping("/push")
    public ResponseEntity<SyncPushResponseDTO> push(@RequestBody SyncRequestDTO request) {
        return ResponseEntity.ok(syncService.push(request));
    }
}
