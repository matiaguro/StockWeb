package com.stockweb.demo.core.usecase;


import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.model.UsuarioList;
import org.springframework.data.domain.PageRequest;

public interface UsuarioService {

        void upDatePassword (Long idUsuario,String newPassword);

        void deleteById(Long idUsuario);

        void updateUsuarioIfExists(Long idUsuario, Usuario usuario, Long newRol);

        UsuarioList getLista (PageRequest pageRequest);

        Usuario getUserById (Long idUsuario);
}
