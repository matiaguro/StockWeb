package com.stockweb.demo.core.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class OrdenList extends PageImpl<Orden> {

    public OrdenList(List<Orden> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

}
