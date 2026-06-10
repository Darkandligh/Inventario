package com.inventario.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncDeletedItemDTO {

    private UUID id;
    private LocalDateTime updatedAt;
}
