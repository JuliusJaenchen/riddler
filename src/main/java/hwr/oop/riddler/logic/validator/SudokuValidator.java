package hwr.oop.riddler.logic.validator;

import hwr.oop.riddler.logic.validator.constraints.Constraint;
import hwr.oop.riddler.logic.validator.constraints.cell.content.UnfilledCellsHavePossiblesConstraint;
import hwr.oop.riddler.logic.validator.constraints.cell.group.CellGroupsHaveNoDuplicatesConstraint;
import hwr.oop.riddler.model.Sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    private final Sudoku sudoku;
    private final Set<Constraint> constraints;

    public SudokuValidator(Sudoku sudoku) {
        this(sudoku, Set.of(new UnfilledCellsHavePossiblesConstraint(), new CellGroupsHaveNoDuplicatesConstraint()));
    }

    public SudokuValidator(Sudoku sudoku, Set<Constraint> constraints) {
        this.sudoku = sudoku;
        this.constraints = new HashSet<>(constraints);
    }

    public boolean isValid() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfiedBy(sudoku)) {
                return false;
            }
        }
        return true;
    }
}
