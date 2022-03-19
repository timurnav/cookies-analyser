package com.cookie.analyser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

public class CsvFileCookieLinesFactory implements DataFactory<CookieLine> {
    private final Path pathToFile;

    public CsvFileCookieLinesFactory(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public Stream<CookieLine> streamData() throws Exception {
        return Files.lines(pathToFile).skip(1)
                .map(this::parse);
    }

    private CookieLine parse(String rawLine) {
        try {
            String[] split = rawLine.split(",");
            return new CookieLine(split[0], OffsetDateTime.parse(split[1]));
        } catch (Exception e) {
            System.err.println("Unable to parse line: " + rawLine);
            throw e;
        }
    }
}
