package com.stockweb.demo.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotProductException  extends  RuntimeException{

    private final Object resourceId;
}
