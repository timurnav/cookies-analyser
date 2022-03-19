package com.cookie.analyser;

import java.util.stream.Stream;

public interface DataFactory<T> {

    Stream<T> streamData() throws Exception;
}
