package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ConflictException;
import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotUserException;
import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.repository.RolRepository;
import com.stockweb.demo.core.repository.UsuarioRepository;
import com.stockweb.demo.core.usecase.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${app.default.role-id}")
    private Long id_rol;

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long createEntity(Usuario usuario) {
        if (exist(usuario.getEmail())) {
            throw new ConflictException("Ya existe una cuenta con esa dirección de correo electrónico: " + usuario.getEmail());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(rolRepository.findById(id_rol).orElseThrow(() -> new ErrorExpected("No se encontro el Rol con id: "+id_rol, HttpStatus.INTERNAL_SERVER_ERROR)));
        return usuarioRepository.save(usuario).getIdUsuario();
    }

    private Boolean exist(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUser (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioRepository.findByEmail(auth.getName()).orElseThrow(() -> new NotUserException(auth.getName()));
    }

}
