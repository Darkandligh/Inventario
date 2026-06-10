package com.inventario.dto.sync;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SyncEntityDTO<T> {

    private List<T> created;
    private List<T> updated;
    private List<UUID> deleted;
}
