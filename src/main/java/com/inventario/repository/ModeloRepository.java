package com.inventario.repository;

import com.inventario.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, UUID> {

    @Query(value = "SELECT * FROM modelos", nativeQuery = true)
    List<Modelo> findAllIncludingDeleted();
}
