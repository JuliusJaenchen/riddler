package hwr.oop.riddler.model;

import hwr.oop.riddler.io.SudokuParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SolvedSudokuTest {
    Sudoku solved;

    @BeforeEach
    void setup() {
        SudokuParser parser = new SudokuParser();
    }

    @Test
    void solved_isFilled() {
        assertTrue(solved.isFilled());
    }
}
