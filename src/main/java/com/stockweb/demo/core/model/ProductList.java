package com.stockweb.demo.core.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class ProductList extends PageImpl<Producto> {
    public ProductList(List<Producto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
