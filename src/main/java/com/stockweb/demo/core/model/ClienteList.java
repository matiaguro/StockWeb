package com.stockweb.demo.core.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class ClienteList extends PageImpl<Cliente> {

    public ClienteList(List<Cliente> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

}
