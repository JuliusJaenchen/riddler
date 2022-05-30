package hwr.oop.riddler.model.constraints.group;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;
import hwr.oop.riddler.model.constraints.Constraint;

import java.util.HashSet;
import java.util.Set;

public class CellGroupHasNoDuplicateConstraint implements Constraint<CellGroup> {
    @Override
    public boolean isSatisfiedBy(CellGroup cellGroup) {
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
