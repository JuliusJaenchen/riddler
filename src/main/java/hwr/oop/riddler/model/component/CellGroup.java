package hwr.oop.riddler.model.component;

import hwr.oop.riddler.model.constraints.group.CellGroupAllCellsAreValidConstraint;
import hwr.oop.riddler.model.constraints.group.CellGroupHasNoDuplicateConstraint;
import hwr.oop.riddler.model.constraints.Constraint;
import hwr.oop.riddler.model.constraints.Validatable;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public record CellGroup(Set<Cell> cells) implements Validatable {
    private static final Set<Constraint<CellGroup>> CONSTRAINTS = Set.of(new CellGroupAllCellsAreValidConstraint(), new CellGroupHasNoDuplicateConstraint());

    public Set<Integer> getCellValues() {
        return cells.stream()
                .filter(Cell::isFilled)
                .map(Cell::getValue)
                .collect(toSet());
    }

    @Override
    public boolean isValid() {
        return CONSTRAINTS.stream()
                .allMatch(constraint -> constraint.isSatisfiedBy(this));
    }
}
