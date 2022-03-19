package com.cookie.analyser;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateMatcher {

    private final OffsetDateTime startOfDay;
    private final OffsetDateTime startOfNextDay;

    public OffsetDateMatcher(String utcDate) {
        this.startOfDay = LocalDate.parse(utcDate).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        this.startOfNextDay = this.startOfDay.plusDays(1);
    }

    public boolean matchingDate(OffsetDateTime time) {
        return !startOfDay.isAfter(time) && startOfNextDay.isAfter(time);
    }
}
