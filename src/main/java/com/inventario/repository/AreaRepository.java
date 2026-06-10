package com.inventario.repository;

import com.inventario.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {

    boolean existsByNombre(String nombre);

    @Query(value = "SELECT * FROM areas", nativeQuery = true)
    List<Area> findAllIncludingDeleted();
}
