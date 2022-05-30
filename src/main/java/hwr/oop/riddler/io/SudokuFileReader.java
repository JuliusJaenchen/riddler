package hwr.oop.riddler.io;

import java.io.*;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class SudokuFileReader implements Closeable {
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public SudokuFileReader(File input) throws FileNotFoundException {
        fileReader = new FileReader(input);
        bufferedReader = new BufferedReader(fileReader);
    }

    public Stream<String> lines() {
        return bufferedReader.lines()
                .map(this::sanitizeLine)
                .filter(not(String::isEmpty));
    }

    private String sanitizeLine(String line) {
        return line.trim()
                .replace(" ", "")
                .replace("_", "0");
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }
}
