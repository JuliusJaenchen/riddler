package hwr.oop.riddler.model.constraints;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.CellGroup;

public class SudokuAllCellGroupsAreValidConstraint implements Constraint<Sudoku> {
    @Override
    public boolean isSatisfiedBy(Sudoku sudoku) {
        return sudoku.getAllCellGroups().stream()
                .allMatch(CellGroup::isValid);
    }
}
