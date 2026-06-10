package com.inventario.repository;

import com.inventario.entity.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipo, UUID> {

    boolean existsByNombre(String nombre);

    @Query(value = "SELECT * FROM tipos_equipo", nativeQuery = true)
    List<TipoEquipo> findAllIncludingDeleted();
}
