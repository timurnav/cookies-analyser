package com.cookie.analyser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

class AnalyseCookiesFileCommandTest {

    @Test
    void positiveCaseTest() throws Exception {
        PrintStream old = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            URL url = Objects.requireNonNull(getClass().getClassLoader().getResource("cookies.csv"));
            String path = Paths.get(url.toURI()).toString();

            AnalyseCookiesFileCommand.main("-f", path, "-d", "2018-12-09");

            Assertions.assertThat(baos.toString().split(System.lineSeparator()))
                    .containsExactly("AtY0laUfhglK3lC7");
        } finally {
            System.setOut(old);
        }
    }
}