package hwr.oop.riddler.model.constraints.group;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;
import hwr.oop.riddler.model.constraints.Constraint;

public class CellGroupAllCellsAreValidConstraint implements Constraint<CellGroup> {
    @Override
    public boolean isSatisfiedBy(CellGroup cellGroup) {
        return cellGroup.cells().stream()
                .allMatch(Cell::isValid);
    }
}
