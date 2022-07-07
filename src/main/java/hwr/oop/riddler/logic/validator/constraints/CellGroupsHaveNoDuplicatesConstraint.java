package hwr.oop.riddler.logic.validator.constraints;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

import java.util.HashSet;
import java.util.Set;

public class CellGroupsHaveNoDuplicatesConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Sudoku sudoku) {
        return sudoku.getAllCellGroups().stream()
                .allMatch(this::cellGroupSatisfiesConstraint);
    }

    private boolean cellGroupSatisfiesConstraint(CellGroup cellGroup) {
        Set<Integer> encounteredValues = new HashSet<>();
        for (Cell cell : cellGroup.cells()) {
            if (cell.isFilled()) {
                if (encounteredValues.contains(cell.getValue()))
                    return false;
                encounteredValues.add(cell.getValue());
            }
        }
        return true;
    }
}
