package com.cookie.analyser;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "cookie-analyser",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Determines the most active cookie from provided file for a specific day")
public class AnalyseCookiesFileCommand implements Callable<Integer> {

    @Option(names = {"-f", "--file"}, description = "parameter for the filename to process")
    private Path fileToProcess;
    @Option(names = {"-d", "--date"}, description = "parameter to specify the date")
    private String utcDate;

    @Override
    public Integer call() throws Exception {
        newAnalyzer(fileToProcess, utcDate)
                .getMostActiveCookies()
                .forEach(System.out::println);

        return 0;
    }

    public static CookieAnalyser newAnalyzer(Path pathToFile, String date) {
        var dataFactory = new CsvFileCookieLinesFactory(pathToFile);
        var dateComparator = new DateToOffsetDateTimeComparator(date);
        return new CookieAnalyser(dataFactory, dateComparator);
    }

    public static void main(String... args) {
        new CommandLine(new AnalyseCookiesFileCommand()).execute(args);
    }
}
