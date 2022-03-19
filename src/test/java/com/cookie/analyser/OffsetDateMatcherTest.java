package com.cookie.analyser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OffsetDateMatcherTest {

    public static final String DATE = "2022-03-19";
    private final DateToOffsetDateTimeComparator matcher = new DateToOffsetDateTimeComparator(DATE);

    @ParameterizedTest
    @MethodSource("testData")
    void afterDayStartedTest(OffsetDateTime testTime, boolean dayStarted) {
        assertThat(matcher.isAfterDayStarted(testTime))
                .as("%s is after day started %s", testTime, DATE)
                .isEqualTo(dayStarted);
    }

    @ParameterizedTest
    @MethodSource("testData")
    void afterDayEndedTest(OffsetDateTime testTime, boolean dummy, boolean dayEnded) {
        assertThat(matcher.isAfterDayEnded(testTime))
                .as("%s after day ended %s", testTime, DATE)
                .isEqualTo(dayEnded);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(OffsetDateTime.parse("2022-03-18T23:59:59+00:00"), false, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T00:00:00+00:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T23:59:59+00:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T00:00:00+00:00"), true, true),
                // Turkey
                Arguments.of(OffsetDateTime.parse("2022-03-19T02:59:59+03:00"), false, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T03:00:00+03:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T02:59:59+03:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T03:00:00+03:00"), true, true),
                // Brazil
                Arguments.of(OffsetDateTime.parse("2022-03-18T20:59:59-03:00"), false, false),
                Arguments.of(OffsetDateTime.parse("2022-03-18T21:00:00-03:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T20:59:59-03:00"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T21:00:00-03:00"), true, true),
                // India
                Arguments.of(OffsetDateTime.parse("2022-03-19T05:29:59+05:30"), false, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T05:30:00+05:30"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T05:29:59+05:30"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T05:30:00+05:30"), true, true),
                // Chatham Islands (New Zealand)
                Arguments.of(OffsetDateTime.parse("2022-03-19T12:44:59+12:45"), false, false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T12:45:00+12:45"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T12:44:59+12:45"), true, false),
                Arguments.of(OffsetDateTime.parse("2022-03-20T12:45:00+12:45"), true, true)
        );
    }
}