package com.stockweb.demo.core.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class UsuarioList extends PageImpl<Usuario> {
    public UsuarioList(List<Usuario> content, Pageable pageable, long total) {
        super(content, pageable, total);

    }
}
