package hwr.oop.riddler.io.parser;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuParserTest {
    SudokuParser parser = new SudokuParser();

    @Test
    void parse_TestSudoku1_Position00Equals8() {
        var inputFile = new File("src/test/resources/parser/parser.1.txt");
        var sudoku = parser.parse(inputFile);
        var cell = sudoku.getCellAt(0, 0);
        assertEquals(8, cell.getValue());
    }

    @Test
    void parse_TestSudoku1_OtherCellsAreEmptyExcept00() {
        var inputFile = new File("src/test/resources/parser/parser.1.txt");
        var sudoku = parser.parse(inputFile);
        var cell1 = sudoku.getCellAt(1, 0);
        var cell2 = sudoku.getCellAt(1, 8);
        var cell3 = sudoku.getCellAt(8, 8);
        assertTrue(cell1.isEmpty());
        assertTrue(cell2.isEmpty());
        assertTrue(cell3.isEmpty());
    }

    @Test
    void parse_InvalidSudoku_UncheckedIOExceptionIsThrown() {
        var inputFile = new File("src/test/resources/parser/parser.unknown.txt");
        assertThrows(UncheckedIOException.class, () -> parser.parse(inputFile));
    }
}
