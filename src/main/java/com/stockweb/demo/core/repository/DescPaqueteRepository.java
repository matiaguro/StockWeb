package com.stockweb.demo.core.repository;

import com.stockweb.demo.core.model.DescPaquete;
import com.stockweb.demo.core.model.Paquete;
import com.stockweb.demo.core.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DescPaqueteRepository extends JpaRepository<DescPaquete,Long> {

    Boolean existsByPaqueteAndProducto (Paquete paquete, Producto producto);

    Optional<DescPaquete> findByPaqueteAndProducto (Paquete paquete, Producto producto);
}
