package com.stockweb.demo.core.model.datetime;

import java.sql.Date;

public class Fecha {
    public static Date get(){
        java.util.Date d = new java.util.Date();
        Date date2 = new Date(d.getTime());
        return date2;
    }
}
