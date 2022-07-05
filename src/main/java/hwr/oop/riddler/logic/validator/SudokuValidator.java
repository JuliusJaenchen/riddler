package hwr.oop.riddler.logic.validator;

import hwr.oop.riddler.logic.validator.constraints.Constraint;
import hwr.oop.riddler.logic.validator.constraints.UnfilledCellsHavePossiblesConstraint;
import hwr.oop.riddler.logic.validator.constraints.CellGroupsHaveNoDuplicatesConstraint;
import hwr.oop.riddler.model.Sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    private final Set<Constraint> constraints;

    public SudokuValidator() {
        this(Set.of(new UnfilledCellsHavePossiblesConstraint(), new CellGroupsHaveNoDuplicatesConstraint()));
    }

    public SudokuValidator(Set<Constraint> constraints) {
        this.constraints = new HashSet<>(constraints);
    }

    public boolean isValid(Sudoku sudoku) {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfiedBy(sudoku))
                return false;
        }
        return true;
    }
}
