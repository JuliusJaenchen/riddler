package hwr.oop.riddler.model.component;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    @Getter
    private final CellGroupIndicators cellGroupIndicators;

    private Integer value;
    @Getter
    private Set<Integer> impossibleValues;

    public Cell(CellGroupIndicators cellGroupIndicators) {
        this.cellGroupIndicators = cellGroupIndicators;
        impossibleValues = new HashSet<>();
    }

    public Cell(int value, CellGroupIndicators cellGroupIndicators) {
        this.cellGroupIndicators = cellGroupIndicators;
        if (value == 0)
            impossibleValues = new HashSet<>();
        else
            this.value = value;
    }

    public Cell(Cell cell) {
        this.value = cell.value;
        if (cell.impossibleValues != null)
            this.impossibleValues = new HashSet<>(cell.impossibleValues);
        else
            this.impossibleValues = null;
        this.cellGroupIndicators = cell.cellGroupIndicators;
    }

    public boolean isFilled() {
        return value != null;
    }

    public boolean isEmpty() {
        return !isFilled();
    }

    public int getValue() {
        if (isEmpty())
            throw new IllegalStateException("empty cell has no value");
        return value;
    }

    public void setValue(int value) {
        if (value < 1)
            throw new IllegalArgumentException("Value must be greater than 0");
        if (this.isFilled())
            throw new IllegalStateException("Value was already set");
        this.impossibleValues = null;
        this.value = value;
    }

    public boolean addImpossibleValue(int impossibleValue) {
        if (impossibleValue < 1)
            throw new IllegalArgumentException("Possible must be greater than 0");
        return impossibleValues.add(impossibleValue);
    }

    public boolean addImpossibles(Set<Integer> impossibleValues) {
        boolean atLeastOneValueWasAdded = false;
        for (int impossibleValue : impossibleValues) {
            boolean valueWasAdded = addImpossibleValue(impossibleValue);
            if (valueWasAdded)
                atLeastOneValueWasAdded = true;
        }
        return atLeastOneValueWasAdded;
    }
}
