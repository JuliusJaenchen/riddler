package hwr.oop.riddler.model.component;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    @Getter
    private final CellGroupIndices cellGroupIndices;

    private Integer value;
    @Getter
    private Set<Integer> impossibles;

    public Cell(CellGroupIndices cellGroupIndices) {
        this.cellGroupIndices = cellGroupIndices;
        impossibles = new HashSet<>();
    }

    public Cell(int value, CellGroupIndices cellGroupIndices) {
        this.cellGroupIndices = cellGroupIndices;
        this.value = value;
    }

    public Cell(Cell cell) {
        this.value = cell.value;
        if (cell.impossibles != null)
            this.impossibles = new HashSet<>(cell.impossibles);
        else
            this.impossibles = null;
        this.cellGroupIndices = cell.cellGroupIndices;
    }

    public boolean isFilled() {
        return value != null;
    }

    public boolean isEmpty() {
        return !isFilled();
    }

    public int getValue() {
        if (isEmpty()) {
            throw new IllegalStateException("empty cell has no value");
        }
        return value;
    }

    public void setValue(int value) {
        if (value < 1)
            throw new IllegalArgumentException("Value must be greater than 0");
        if (this.isFilled())
            throw new IllegalStateException("Value was already set");
        this.impossibles = null;
        this.value = value;
    }

    public boolean addImpossible(int impossibleValue) {
        if (impossibleValue < 1)
            throw new IllegalArgumentException("Possible must be greater than 0");
        return impossibles.add(impossibleValue);
    }

    public boolean addImpossibles(Set<Integer> impossibleValues) {
        boolean atLeastOneValueWasAdded = false;
        for (int impossibleValue : impossibleValues) {
            boolean valueWasAdded = addImpossible(impossibleValue);
            if (valueWasAdded)
                atLeastOneValueWasAdded = true;
        }
        return atLeastOneValueWasAdded;
    }
}
