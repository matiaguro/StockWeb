package com.stockweb.demo.core.repository;


import com.stockweb.demo.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);

}
