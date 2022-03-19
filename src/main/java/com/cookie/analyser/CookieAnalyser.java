package com.cookie.analyser;

import java.util.List;

public class CookieAnalyser {

    private final DataFactory<CookieLine> dataFactory;
    private final DateToOffsetDateTimeComparator dateComparator;

    public CookieAnalyser(DataFactory<CookieLine> dataFactory, DateToOffsetDateTimeComparator dateComparator) {
        this.dataFactory = dataFactory;
        this.dateComparator = dateComparator;
    }

    public List<String> getMostActiveCookies() throws Exception {
        return dataFactory.streamData()
                .dropWhile(cookieLine -> dateComparator.isAfterDayEnded(cookieLine.getTime()))
                .takeWhile(cookieLine -> dateComparator.isAfterDayStarted(cookieLine.getTime()))
                .map(CookieLine::getCookie)
                .collect(new MostCommonItemListCollector<>());
    }
}
