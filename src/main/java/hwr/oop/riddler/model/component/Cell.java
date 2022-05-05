package hwr.oop.riddler.model.component;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
public class Cell {
    private int value = 0;
    @Getter
    private Set<Integer> impossibles = new HashSet<>();;

    public Cell(int value) {
        this.value = value;
    }

    public Cell() {
    }

    public Cell(Cell cell) {
        this.value = cell.value;
        if (cell.isEmpty())
            this.impossibles = new HashSet<>(cell.impossibles);
    }

    public boolean isFilled() {
        return value != 0;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public void setValue(int value) {
        if (value < 1)
            throw new IllegalArgumentException("Value must be greater than 0");
        if (this.value != 0)
            throw new IllegalStateException("Value was already set");
        this.value = value;
    }

    public boolean addImpossible(int impossibleValue) {
        return impossibles.add(impossibleValue);
    }

    public boolean addImpossibles(Set<Integer> impossibleValues) {
        return impossibles.addAll(impossibleValues);
    }

    public int getValue() {
        return value;
    }
}
