package com.stockweb.demo.core.model.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Fecha {
    public static Date get(){
        TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        return  Calendar.getInstance(timeZone).getTime();
    }
}
