package hwr.oop.riddler.model;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SolvedSudokuTest {
    Sudoku solvedSudoku;
    SudokuParser parser = new SudokuParser();

    @BeforeEach
    void setup() {
        solvedSudoku = parser.parse("src/test/resources/solvedSudoku.txt");
    }

    @Test
    void solved_isFilled() {
        assertTrue(solvedSudoku.isFilled());
    }
}
