package com.inventario.repository;

import com.inventario.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, UUID> {

    Optional<Equipo> findBySerial(String serial);

    boolean existsBySerial(String serial);

    @Query(value = "SELECT * FROM equipos", nativeQuery = true)
    List<Equipo> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM equipos WHERE id = :id", nativeQuery = true)
    Optional<Equipo> findByIdIncludingDeleted(@Param("id") UUID id);
}
