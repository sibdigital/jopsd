package ru.sibdigital.jopsd.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {
    public static Integer getQuarterByMonth(Integer monthValue) {
        if ((monthValue >= 1) && (monthValue <= 3)) {
            return 1;
        } else if ((monthValue >= 4) && (monthValue <= 6)) {
            return 2;
        } else if ((monthValue >= 7) && (monthValue <= 9)) {
            return 3;
        } else if ((monthValue >= 10) && (monthValue <= 12)) {
            return 4;
        }  else {
            return null;
        }
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        if (dateToConvert != null) {
            return dateToConvert.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } else {
            return null;
        }
    }
}
