package com.stockweb.demo.core.usecase;

import com.stockweb.demo.core.model.Usuario;

public interface AuthService {

    Long createEntity(Usuario usuario);

    public Usuario getUser ();
}
