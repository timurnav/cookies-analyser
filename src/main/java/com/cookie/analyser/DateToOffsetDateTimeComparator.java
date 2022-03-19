package com.cookie.analyser;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateToOffsetDateTimeComparator {

    private final OffsetDateTime startOfDay;
    private final OffsetDateTime startOfNextDay;

    public DateToOffsetDateTimeComparator(String utcDate) {
        this.startOfDay = LocalDate.parse(utcDate).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        this.startOfNextDay = this.startOfDay.plusDays(1);
    }

    public boolean isAfterDayStarted(OffsetDateTime time) {
        return !time.isBefore(startOfDay);
    }

    public boolean isAfterDayEnded(OffsetDateTime time) {
        return !startOfNextDay.isAfter(time);
    }
}
