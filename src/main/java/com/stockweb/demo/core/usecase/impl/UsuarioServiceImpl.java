package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.exception.ErrorExpected;
import com.stockweb.demo.config.exception.NotFoundException;
import com.stockweb.demo.config.exception.NotUserException;
import com.stockweb.demo.core.model.Rol;
import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.model.UsuarioList;
import com.stockweb.demo.core.repository.RolRepository;
import com.stockweb.demo.core.repository.UsuarioRepository;
import com.stockweb.demo.core.usecase.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final RolRepository rolRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public void upDatePassword(Long idUsuario, String newPassword) {
        usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
            usuarioJpa.setPassword(passwordEncoder.encode(newPassword));
            return usuarioRepository.save(usuarioJpa);
        }).orElseThrow(() -> new NotUserException(idUsuario));
    }

    @Override
    @Transactional
    public void deleteById(Long idUsuario) {
        usuarioRepository.findById(idUsuario).ifPresent(usuarioRepository::delete);
    }

    @Override
    @Transactional
    public void updateUsuarioIfExists(Long idUsuario, Usuario usuario, Long idNewRol) {
            usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
                if (usuario.getFirstname() != null) { usuarioJpa.setFirstname(usuario.getFirstname());}
                if (usuario.getEmail() != null) { usuarioJpa.setEmail(usuario.getEmail()); }
                if (usuario.getLastname() != null) { usuarioJpa.setLastname(usuario.getLastname()); }
                return usuarioRepository.save(usuarioJpa);
            });
        if (idNewRol != null) {
            Rol rol = rolRepository.findById(idNewRol).orElseThrow(() -> new ErrorExpected("El rol con id: "+idNewRol + "No existe", HttpStatus.NOT_FOUND));
            usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
                usuarioJpa.setRol(rol);
                return usuarioRepository.save(usuarioJpa);
            });
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioList getLista(PageRequest pageRequest) {
        Page<Usuario> page = usuarioRepository.findAll(pageRequest);
        return new UsuarioList(page.getContent(), pageRequest, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUserById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotUserException(idUsuario));
    }


}













