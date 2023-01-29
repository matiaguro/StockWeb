package com.stockweb.demo.core.usecase.impl;

import com.stockweb.demo.config.NotFoundException;
import com.stockweb.demo.core.model.ProductList;
import com.stockweb.demo.core.model.Producto;
import com.stockweb.demo.core.model.Rol;
import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.model.UsuarioList;
import com.stockweb.demo.core.repository.RolRepository;
import com.stockweb.demo.core.repository.UsuarioRepository;
import com.stockweb.demo.core.usecase.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public Long createEntity(String usuario, String password, Long idRol) {
        Rol rol = rolRepository.findById(idRol).orElseThrow(() -> new NotFoundException(idRol));
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setPassword(password);
        user.setRol(rol);
        return usuarioRepository.save(user).getIdUsuario();
    }

    @Override
    @Transactional
    public void upDatePassword(Long idUsuario, String newPassword) {
        usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
            usuarioJpa.setPassword(newPassword);
            return usuarioRepository.save(usuarioJpa);
        }).orElseThrow(() -> new NotFoundException(idUsuario));
    }

    @Override
    @Transactional
    public void deleteById(Long idUsuario) {
        usuarioRepository.findById(idUsuario).ifPresent(usuarioRepository::delete);
    }

    @Override
    @Transactional
    public void updateUsuarioIfExists(Long idUsuario, String nameUsuario, Long newLong) {

        if (!nameUsuario.isEmpty()) {
            usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
                usuarioJpa.setUsuario(nameUsuario);
                return usuarioRepository.save(usuarioJpa);
            });
        }

        if (newLong != null) {
            Rol rol = rolRepository.findById(newLong).orElseThrow(() -> new NotFoundException(newLong));
            usuarioRepository.findById(idUsuario).map(usuarioJpa -> {
                usuarioJpa.setRol(rol);
                return usuarioRepository.save(usuarioJpa);
            });
        }
    }

    @Override
    @Transactional
    public UsuarioList getLista (PageRequest pageRequest){
        Page<Usuario> page = usuarioRepository.findAll(pageRequest);
        return new UsuarioList(page.getContent(), pageRequest, page.getTotalElements());
    }
}













