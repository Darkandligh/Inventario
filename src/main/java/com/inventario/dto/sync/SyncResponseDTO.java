package com.inventario.dto.sync;

import com.inventario.dto.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SyncResponseDTO {

    private LocalDateTime timestamp;
    private SyncEntityDTO<EquipoResponseDTO> equipos;
    private SyncEntityDTO<AreaResponseDTO> areas;
    private SyncEntityDTO<TipoEquipoResponseDTO> tipos;
    private SyncEntityDTO<MarcaResponseDTO> marcas;
    private SyncEntityDTO<ModeloResponseDTO> modelos;
}
