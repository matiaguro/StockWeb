package com.stockweb.demo.core.repository;


import com.stockweb.demo.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
