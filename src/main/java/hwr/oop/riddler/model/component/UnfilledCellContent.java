package hwr.oop.riddler.model.component;

import hwr.oop.riddler.model.constraints.Constraint;
import hwr.oop.riddler.model.constraints.cell.content.UnfilledCellContentHasPossiblesConstraint;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UnfilledCellContent implements CellContent {

    @Getter
    private final Set<Integer> impossibles = new HashSet<>();
    public void addImpossible(int impossible) {
        impossibles.add(impossible);
    }

    public boolean addImpossibles(Collection<Integer> impossibles) {
        return this.impossibles.addAll(impossibles);
    }

    private static final Set<Constraint<UnfilledCellContent>> CONSTRAINTS = Set.of(new UnfilledCellContentHasPossiblesConstraint());

    @Override
    public boolean isValid() {
        for (Constraint<UnfilledCellContent> constraint : CONSTRAINTS) {
            if(!constraint.isSatisfiedBy(this))
                return false;
        }
        return true;
    }
}
