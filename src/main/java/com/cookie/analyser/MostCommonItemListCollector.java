package com.cookie.analyser;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MostCommonItemListCollector<T> implements Collector<T, Map<T, Integer>, List<T>> {

    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return LinkedHashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (hits, item) -> hits.merge(item, 1, Integer::sum);
    }

    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (one, another) -> {
            another.forEach((key, val) -> one.merge(key, val, Integer::sum));
            another.clear();
            return one;
        };
    }

    @Override
    public Function<Map<T, Integer>, List<T>> finisher() {
        return hits -> {
            int max = hits.values().stream()
                    .max(Comparator.naturalOrder())
                    .orElse(0);
            return hits.entrySet().stream()
                    .filter(entry -> entry.getValue() == max)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
