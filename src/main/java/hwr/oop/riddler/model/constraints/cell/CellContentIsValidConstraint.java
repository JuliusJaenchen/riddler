package hwr.oop.riddler.model.constraints.cell;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.constraints.Constraint;

public class CellContentIsValidConstraint implements Constraint<Cell> {
    @Override
    public boolean isSatisfiedBy(Cell cell) {
        return cell.getContent().isValid();
    }
}
