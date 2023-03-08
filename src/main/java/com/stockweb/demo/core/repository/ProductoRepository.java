package com.stockweb.demo.core.repository;

import com.stockweb.demo.core.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
