package com.cookie.analyser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

class OffsetDateMatcherTest {

    public static final String DATE = "2022-03-19";
    private final OffsetDateMatcher matcher = new OffsetDateMatcher(DATE);

    @ParameterizedTest
    @MethodSource("testData")
    void dateMatchingTest(OffsetDateTime testTime, boolean expectedResult) {
        Assertions.assertThat(matcher.matchingDate(testTime))
                .as("%s comparing to %s", DATE, testTime)
                .isEqualTo(expectedResult);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(OffsetDateTime.parse("2022-03-18T23:59:59+00:00"), false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T00:00:00+00:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-19T23:59:59+00:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T00:00:00+00:00"), false),
                // Turkey
                Arguments.of(OffsetDateTime.parse("2022-03-19T02:59:59+03:00"), false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T03:00:00+03:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T02:59:59+03:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T03:00:00+03:00"), false),
                // Brazil
                Arguments.of(OffsetDateTime.parse("2022-03-18T20:59:59-03:00"), false),
                Arguments.of(OffsetDateTime.parse("2022-03-18T21:00:00-03:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-19T20:59:59-03:00"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-19T21:00:00-03:00"), false),
                // India
                Arguments.of(OffsetDateTime.parse("2022-03-19T05:29:59+05:30"), false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T05:30:00+05:30"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T05:29:59+05:30"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T05:30:00+05:30"), false),
                // Chatham Islands (New Zealand)
                Arguments.of(OffsetDateTime.parse("2022-03-19T12:44:59+12:45"), false),
                Arguments.of(OffsetDateTime.parse("2022-03-19T12:45:00+12:45"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T12:44:59+12:45"), true),
                Arguments.of(OffsetDateTime.parse("2022-03-20T12:45:00+12:45"), false)
        );
    }

}