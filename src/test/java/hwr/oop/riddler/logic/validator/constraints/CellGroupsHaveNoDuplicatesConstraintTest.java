package hwr.oop.riddler.logic.validator.constraints;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellGroupsHaveNoDuplicatesConstraintTest {
    CellGroupsHaveNoDuplicatesConstraint constraint = new CellGroupsHaveNoDuplicatesConstraint();
    SudokuParser parser = new SudokuParser();

    @Test
    void isSatisfiedBy_sudokuWithDuplicate() {
        var invalidSudoku = parser.parse("src/test/resources/invalidSudokus/sudoku-with-duplicate.txt");
        assertFalse(constraint.isSatisfiedBy(invalidSudoku));
    }

    @Test
    void isSatisfiedBy_sudokuWithoutDuplicate() {
        var validSudoku = parser.parse("src/test/resources/unsolvedSudokus/sudoku.1.txt");
        assertTrue(constraint.isSatisfiedBy(validSudoku));
    }
}
