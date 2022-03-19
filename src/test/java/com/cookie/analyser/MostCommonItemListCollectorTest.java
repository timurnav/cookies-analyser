package com.cookie.analyser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MostCommonItemListCollectorTest {

    @ParameterizedTest
    @MethodSource("testData")
    void dateMatchingTest(List<String> dataList, List<String> expectedResult) {
        List<String> actual = dataList.stream().collect(new MostCommonItemListCollector<>());
        Assertions.assertThat(actual)
                .containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of("A", "A", "A"), List.of("A")),
                Arguments.of(List.of("A", "B", "A"), List.of("A")),
                Arguments.of(List.of("A", "B"), List.of("A", "B")),
                Arguments.of(List.of("A", "B", "A", "B"), List.of("A", "B")),
                Arguments.of(List.of("A", "B", "A", "B", "C"), List.of("A", "B")),
                Arguments.of(List.of("A", "B", "A", "B", "C", "A"), List.of("A")),
                Arguments.of(List.of("A", "A", "B", "B", "B", "C"), List.of("B")),
                Arguments.of(List.of("A", "B", "A", "B", "C", "C"), List.of("A", "B", "C")),
                Arguments.of(List.of("A"), List.of("A"))
        );
    }
}