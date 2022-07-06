package hwr.oop.riddler.io;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SudokuPrinterTest {
    private final SudokuParser parser = new SudokuParser();

    @Test
    void print_fullSudoku_EqualsExpectedOutput() throws IOException {
        var inputFile = new File("src/test/resources/parser/parser.full.txt");
        var sudoku = parser.parse(inputFile);

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
    void print_emptySudoku_EqualsExpectedOutput() throws IOException {
        var inputFile = new File("src/test/resources/parser/parser.1.txt");
        var sudoku = parser.parse(inputFile);

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
    void print_invalidOutputStream_ThrowsUncheckedIOEXception() throws IOException {
        var inputFile = new File("src/test/resources/parser/parser.1.txt");
        var sudoku = parser.parse(inputFile);

        var pipeInput = new PipedInputStream();
        var out = new BufferedOutputStream(new PipedOutputStream(pipeInput));

        out.close();

        SudokuPrinter printer = new SudokuPrinter(out);
        assertThrows(UncheckedIOException.class, () -> printer.print(sudoku));
    }
}
