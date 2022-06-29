package com.github.ubaifadhli.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DatetimeHelper {
    public static String getCurrentDatetime() {
        return ZonedDateTime.now(ZoneOffset.of("+07:00")).format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
