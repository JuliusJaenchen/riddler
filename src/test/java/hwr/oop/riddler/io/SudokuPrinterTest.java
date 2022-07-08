package hwr.oop.riddler.io;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuPrinterTest {
    private final SudokuParser parser = new SudokuParser();

    @Test
    void print_fullSudoku_equalsExpectedOutput() throws IOException {
        var sudoku = parser.parse("src/test/resources/parser/parser.full.txt");

        var pipeInput = new PipedInputStream();
        var out = new BufferedOutputStream(new PipedOutputStream(pipeInput));

        var reader = new BufferedReader(new InputStreamReader(pipeInput));

        SudokuPrinter printer = new SudokuPrinter(out);
        printer.print(sudoku);

        var compareToFileReader = new BufferedReader(new FileReader("src/test/resources/printer/sampleoutput.full.txt"));

        var lines = reader.lines().map(String::trim);

        assertEquals(lines.toList(), compareToFileReader.lines().toList());
    }


    @Test
    void print_emptySudoku_equalsExpectedOutput() throws IOException {
        var sudoku = parser.parse("src/test/resources/parser/parser.1.txt");

        var pipeInput = new PipedInputStream();
        var out = new BufferedOutputStream(new PipedOutputStream(pipeInput));

        var reader = new BufferedReader(new InputStreamReader(pipeInput));

        SudokuPrinter printer = new SudokuPrinter(out);
        printer.print(sudoku);

        var compareToFileReader = new BufferedReader(new FileReader("src/test/resources/printer/sampleoutput.1.txt"));

        var lines = reader.lines().map(String::trim);

        assertEquals(lines.toList(), compareToFileReader.lines().toList());
    }

    @Test
    void print_invalidOutputStream_throwsUncheckedIOEXception() throws IOException {
        var sudoku = parser.parse("src/test/resources/parser/parser.1.txt");

        var pipeInput = new PipedInputStream();
        var out = new BufferedOutputStream(new PipedOutputStream(pipeInput));

        out.close();

        SudokuPrinter printer = new SudokuPrinter(out);
        assertThrows(UncheckedIOException.class, () -> printer.print(sudoku));
    }
}
