package com.inventario.repository;

import com.inventario.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, UUID> {

    Optional<Equipo> findBySerial(String serial);

    boolean existsBySerial(String serial);
}
