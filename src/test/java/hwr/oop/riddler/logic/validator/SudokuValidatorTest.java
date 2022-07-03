package hwr.oop.riddler.logic.validator;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuValidatorTest {
    SudokuParser parser = new SudokuParser();
    SudokuValidator validator = new SudokuValidator();

    @Test
    void solvedSudoku_isValid() {
        var solvedSudoku = parser.parse("src/test/resources/solvedSudoku.txt");
        assertTrue(validator.isValid(solvedSudoku));
    }

    @Test
    void invalidSudoku_isNotValid() {
        var invalidSudoku = parser.parse("src/test/resources/invalidSudokus/sudoku-with-duplicate.txt");
        assertFalse(validator.isValid(invalidSudoku));
    }

    @Test
    void unsolvedSudoku_isValid() {
        var unsolvedSudoku = parser.parse("src/test/resources/unsolvedSudokus/sudoku.1.txt");
        assertTrue(validator.isValid(unsolvedSudoku));
    }
}
