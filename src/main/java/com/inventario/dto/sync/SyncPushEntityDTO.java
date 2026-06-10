package com.inventario.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncPushEntityDTO<T> {

    private List<T> created;
    private List<T> updated;
    private List<SyncDeletedItemDTO> deleted;
}
