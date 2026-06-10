package com.inventario.dto.sync;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SyncPushResponseDTO {

    private String status;
}
