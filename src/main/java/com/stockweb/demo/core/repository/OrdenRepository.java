package com.stockweb.demo.core.repository;

import com.stockweb.demo.core.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository <Orden, Long> {
}
