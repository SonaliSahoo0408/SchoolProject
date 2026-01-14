package com.nimblix.SchoolPEPProject.Util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SchoolUtil {

    public static LocalDateTime changeCurrentTimeToLocalDateTimeFromGmtToIST() {
        LocalDateTime gmtTime = LocalDateTime.now(ZoneOffset.UTC);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime istTime = gmtTime.atZone(ZoneOffset.UTC).withZoneSameInstant(istZone).toLocalDateTime();

        return istTime;
    }

    public static String changeCurrentTimeToLocalDateFromGmtToISTInString() {
        LocalDateTime gmtTime = LocalDateTime.now(ZoneOffset.UTC);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime istTime = gmtTime.atZone(ZoneOffset.UTC).withZoneSameInstant(istZone).toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return istTime.format(formatter);
    }
}
