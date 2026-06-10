package com.inventario.service;

import com.inventario.dto.sync.*;
import com.inventario.entity.*;
import com.inventario.exception.*;
import com.inventario.mapper.*;
import com.inventario.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncService {

    private final EquipoRepository equipoRepository;
    private final AreaRepository areaRepository;
    private final TipoEquipoRepository tipoEquipoRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    private final EquipoMapper equipoMapper;
    private final AreaMapper areaMapper;
    private final TipoEquipoMapper tipoEquipoMapper;
    private final MarcaMapper marcaMapper;
    private final ModeloMapper modeloMapper;

    // -------------------------------------------------------------------------
    // PULL
    // -------------------------------------------------------------------------

    @Transactional(readOnly = true)
    public SyncResponseDTO pull(LocalDateTime since) {
        return SyncResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .equipos(buildSync(equipoRepository.findAllIncludingDeleted(), since,
                        Equipo::getId, equipoMapper::toResponseDTO))
                .areas(buildSync(areaRepository.findAllIncludingDeleted(), since,
                        Area::getId, areaMapper::toResponseDTO))
                .tipos(buildSync(tipoEquipoRepository.findAllIncludingDeleted(), since,
                        TipoEquipo::getId, tipoEquipoMapper::toResponseDTO))
                .marcas(buildSync(marcaRepository.findAllIncludingDeleted(), since,
                        Marca::getId, marcaMapper::toResponseDTO))
                .modelos(buildSync(modeloRepository.findAllIncludingDeleted(), since,
                        Modelo::getId, modeloMapper::toResponseDTO))
                .build();
    }

    private <E extends AuditableEntity, D> SyncEntityDTO<D> buildSync(
            List<E> records,
            LocalDateTime since,
            Function<E, UUID> idExtractor,
            Function<E, D> toDTO) {

        return SyncEntityDTO.<D>builder()
                .created(records.stream()
                        .filter(e -> e.getDeletedAt() == null && e.getCreatedAt().isAfter(since))
                        .map(toDTO)
                        .toList())
                .updated(records.stream()
                        .filter(e -> e.getDeletedAt() == null
                                && e.getUpdatedAt().isAfter(since)
                                && !e.getCreatedAt().isAfter(since))
                        .map(toDTO)
                        .toList())
                .deleted(records.stream()
                        .filter(e -> e.getDeletedAt() != null && e.getDeletedAt().isAfter(since))
                        .map(idExtractor)
                        .toList())
                .build();
    }

    // -------------------------------------------------------------------------
    // PUSH
    // -------------------------------------------------------------------------

    @Transactional
    public SyncPushResponseDTO push(SyncRequestDTO request) {
        if (request.getEquipos() != null) {
            processEquipos(request.getEquipos());
        }
        return SyncPushResponseDTO.builder().status("success").build();
    }

    private void processEquipos(SyncPushEntityDTO<EquipoPushDTO> syncEquipos) {
        // 1. DELETED primero: libera seriales antes de crear/actualizar
        if (syncEquipos.getDeleted() != null) {
            for (SyncDeletedItemDTO item : syncEquipos.getDeleted()) {
                try {
                    softDeleteEquipo(item.getId(), item.getUpdatedAt());
                } catch (Exception e) {
                    log.error("Push - error soft-delete equipo {}: {}", item.getId(), e.getMessage());
                }
            }
        }

        // 2. UPDATED
        if (syncEquipos.getUpdated() != null) {
            for (EquipoPushDTO dto : syncEquipos.getUpdated()) {
                try {
                    upsertEquipo(dto);
                } catch (Exception e) {
                    log.error("Push - error updated equipo {}: {}", dto.getId(), e.getMessage());
                }
            }
        }

        // 3. CREATED
        if (syncEquipos.getCreated() != null) {
            for (EquipoPushDTO dto : syncEquipos.getCreated()) {
                try {
                    upsertEquipo(dto);
                } catch (Exception e) {
                    log.error("Push - error created equipo {}: {}", dto.getId(), e.getMessage());
                }
            }
        }
    }

    private void softDeleteEquipo(UUID id, LocalDateTime clientUpdatedAt) {
        equipoRepository.findByIdIncludingDeleted(id).ifPresent(equipo -> {
            if (equipo.isDeleted()) return;
            if (clientUpdatedAt.isAfter(equipo.getUpdatedAt())) {
                equipo.softDelete();
                equipoRepository.save(equipo);
            } else {
                log.debug("Push - soft-delete ignorado para equipo {} (servidor más reciente)", id);
            }
        });
    }

    private void upsertEquipo(EquipoPushDTO dto) {
        TipoEquipo tipo = tipoEquipoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new TipoEquipoNotFoundException(dto.getTipoId()));
        Marca marca = marcaRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(dto.getMarcaId()));
        Modelo modelo = modeloRepository.findById(dto.getModeloId())
                .orElseThrow(() -> new ModeloNotFoundException(dto.getModeloId()));
        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new AreaNotFoundException(dto.getAreaId()));

        Equipo equipo = equipoRepository.findByIdIncludingDeleted(dto.getId()).orElse(null);

        if (equipo == null) {
            equipo = Equipo.builder().id(dto.getId()).build();
        } else if (!dto.getUpdatedAt().isAfter(equipo.getUpdatedAt())) {
            log.debug("Push - upsert ignorado para equipo {} (servidor más reciente)", dto.getId());
            return;
        }

        equipo.setTipo(tipo);
        equipo.setMarca(marca);
        equipo.setModelo(modelo);
        equipo.setSerial(dto.getSerial());
        equipo.setEstado(dto.getEstado());
        equipo.setArea(area);
        equipo.setObservaciones(dto.getObservaciones());
        equipo.setDeletedAt(null);

        equipoRepository.save(equipo);
    }
}
