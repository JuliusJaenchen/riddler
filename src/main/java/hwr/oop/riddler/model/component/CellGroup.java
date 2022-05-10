package hwr.oop.riddler.model.component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A CellGroup is either a Row, Column, or a Box. These are treated Identically. A polymorphic implementation would
 * have lead to duplicate code.
 */
public class CellGroup {
    private final Set<Cell> cells;

    public CellGroup(Set<Cell> contents) {
        this.cells = new HashSet<>(contents);
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public Set<Cell> getUnsolvedCells() {
        return cells.stream().filter(Cell::isEmpty).collect(Collectors.toSet());
    }

    public Set<Integer> getAllValues() {
        return cells.stream().filter(Cell::isFilled).map(Cell::getValue).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellGroup cellGroup = (CellGroup) o;
        return cells.equals(cellGroup.cells);
    }

    @Override
    public int hashCode() {
        return cells.hashCode();
    }
}
