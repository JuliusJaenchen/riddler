package hwr.oop.riddler.model.constraints.cell.content;

import hwr.oop.riddler.model.component.UnfilledCellContent;
import hwr.oop.riddler.model.constraints.Constraint;

public class UnfilledCellContentHasPossiblesConstraint implements Constraint<UnfilledCellContent> {
    @Override
    public boolean isSatisfiedBy(UnfilledCellContent object) {
        return object.getImpossibles().size() < 9;
    }
}
