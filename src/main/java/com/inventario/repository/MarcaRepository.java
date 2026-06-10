package com.inventario.repository;

import com.inventario.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, UUID> {

    boolean existsByNombre(String nombre);

    @Query(value = "SELECT * FROM marcas", nativeQuery = true)
    List<Marca> findAllIncludingDeleted();
}
