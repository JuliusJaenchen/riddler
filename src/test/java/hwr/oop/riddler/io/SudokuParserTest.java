package hwr.oop.riddler.io;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuParserTest {
    SudokuParser parser = new SudokuParser();

    @Test
    void parser() {
        var parserFile = new File("src/test/resources/parser/parser.1.txt");
        var sudoku = parser.parse(parserFile);
        assertEquals(8, sudoku.getCellAt(0,0).getValue());
        assertTrue( sudoku.getCellAt(1,0).isEmpty());
    }

}
