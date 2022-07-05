package hwr.oop.riddler.logic.validator.constraints;

import hwr.oop.riddler.io.parser.SudokuParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnfilledCellsHavePossiblesConstraintTest {
    SudokuParser parser = new SudokuParser();
    UnfilledCellsHavePossiblesConstraint constraint = new UnfilledCellsHavePossiblesConstraint();

    @Test
    void isSatisfiedBy_oneCellWithoutPossibles() {
        var invalidSudoku = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
        var invalidCell = invalidSudoku.getCellAt(0, 1);
        invalidCell.addImpossibleValue(1);
        invalidCell.addImpossibleValue(2);
        invalidCell.addImpossibleValue(3);
        invalidCell.addImpossibleValue(4);
        assertFalse(constraint.isSatisfiedBy(invalidSudoku));
    }

    @Test
    void isSatisfiedBy_oneCellWithOnePossibleValue() {
        var validSudoku = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
        var validCell = validSudoku.getCellAt(0, 1);
        validCell.addImpossibleValue(1);
        validCell.addImpossibleValue(2);
        validCell.addImpossibleValue(3);
        assertTrue(constraint.isSatisfiedBy(validSudoku));
    }

    @Test
    void isSatisfiedBy_unsolvedSudoku() {
        var validSudoku = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
        assertTrue(constraint.isSatisfiedBy(validSudoku));
    }
}