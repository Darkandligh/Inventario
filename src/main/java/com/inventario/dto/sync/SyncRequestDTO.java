package com.inventario.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncRequestDTO {

    private SyncPushEntityDTO<EquipoPushDTO> equipos;
}
