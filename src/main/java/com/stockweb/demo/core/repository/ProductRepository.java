package com.stockweb.demo.core.repository;

import com.stockweb.demo.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
