package com.cookie.analyser;

import java.time.OffsetDateTime;

public class CookieLine {

    private final String cookie;
    private final OffsetDateTime time;

    public CookieLine(String cookie, OffsetDateTime time) {
        this.cookie = cookie;
        this.time = time;
    }

    public String getCookie() {
        return cookie;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return cookie + ',' + time;
    }
}
