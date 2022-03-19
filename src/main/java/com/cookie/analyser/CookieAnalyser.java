package com.cookie.analyser;

import java.util.List;

public class CookieAnalyser {

    private final DataFactory<CookieLine> dataFactory;
    private final OffsetDateMatcher dateMatcher;

    public CookieAnalyser(DataFactory<CookieLine> dataFactory, OffsetDateMatcher dateMatcher) {
        this.dataFactory = dataFactory;
        this.dateMatcher = dateMatcher;
    }

    public List<String> getMostActiveCookies() throws Exception {
        return dataFactory.streamData()
                .filter(cookieLine -> dateMatcher.matchingDate(cookieLine.getTime()))
                .map(CookieLine::getCookie)
                .collect(new MostCommonItemListCollector<>());
    }
}
