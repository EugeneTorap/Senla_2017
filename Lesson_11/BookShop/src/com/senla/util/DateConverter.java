package com.senla.util;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConverter {

    public static Date valueOf(java.util.Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Belarus/Minsk");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        return Date.valueOf(zdt.toLocalDate());
    }
}
