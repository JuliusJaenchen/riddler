package hwr.oop.riddler.model;

import hwr.oop.riddler.io.SudokuParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SolvedSudokuTest {
    Sudoku solved;

    private final int[][] solvedFourByFourArray = {
            {4, 1, 2, 3},
            {3, 2, 1, 4},
            {2, 3, 4, 1},
            {1, 4, 3, 2},
    };

    @BeforeEach
    void setup() {
        SudokuParser parser = new SudokuParser();
        solved = parser.parse(solvedFourByFourArray);
    }

    @Test
    void solved_isFilled() {
        assertTrue(solved.isFilled());
    }
}
