package com.stockweb.demo.core.repository;

import com.stockweb.demo.core.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
