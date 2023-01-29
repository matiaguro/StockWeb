package com.stockweb.demo.core.usecase;


import com.stockweb.demo.core.model.UsuarioList;
import org.springframework.data.domain.PageRequest;

public interface UsuarioService {

        Long createEntity(String usuario,String password,Long idRol);

        void upDatePassword (Long idUsuario,String newPassword);

        void deleteById(Long idUsuario);

        void updateUsuarioIfExists(Long idUsuario, String usuario, Long newRol);

        UsuarioList getLista (PageRequest pageRequest);
}
