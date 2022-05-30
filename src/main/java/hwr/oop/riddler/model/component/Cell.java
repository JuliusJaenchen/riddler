package hwr.oop.riddler.model.component;

import hwr.oop.riddler.model.constraints.Constraint;
import hwr.oop.riddler.model.constraints.Validatable;
import hwr.oop.riddler.model.constraints.cell.CellContentIsValidConstraint;
import lombok.Getter;

import java.util.Set;

public class Cell implements Validatable {
    @Getter
    private CellContent content;
    @Getter
    private final CellPosition position;

    public Cell(CellPosition position) {
        this.position = position;
        content = new UnfilledCellContent();
    }

    public Cell(int value, CellPosition position) {
        this.content = FilledCellContent.fromInteger(value);
        this.position = position;
    }

    public Cell(Cell cell) {
        this.content = cell.content; //TODO clone content
        this.position = cell.position;
    }

    public boolean isFilled() {
        return content instanceof FilledCellContent;
    }

    public boolean isEmpty() {
        return !isFilled();
    }

    public void setValue(int value) {
        if (value < 1)
            throw new IllegalArgumentException("Value must be greater than 0");
        if (this.isFilled())
            throw new IllegalStateException("Value was already set");
        this.content = new FilledCellContent(value);
    }

    public boolean addImpossible(int impossibleValue) {
        if (impossibleValue < 1)
            throw new IllegalArgumentException("Possible must be greater than 0");
        throw new UnsupportedOperationException("removed function");
    }

    public int getValue() {
        if (content instanceof FilledCellContent filledCellContent) {
            return filledCellContent.value();
        }
        throw new IllegalStateException("empty cell has no value");
    }

    private static final Set<Constraint<Cell>> CONSTRAINTS = Set.of(new CellContentIsValidConstraint());

    @Override
    public boolean isValid() {
        return CONSTRAINTS.stream().allMatch(cellConstraint -> cellConstraint.isSatisfiedBy(this));
    }
}
