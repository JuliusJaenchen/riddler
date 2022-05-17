package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.*;
import lombok.Getter;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Sudoku {
    private final Set<Cell> cells;
    @Getter
    private final int size;

    public Sudoku(Set<Cell> cells) {
        this.cells = cells;
        size = (int) Math.sqrt(cells.size());
    }

    public Sudoku(Sudoku sudoku) {
        this.size = sudoku.size;
        this.cells = sudoku.cells.stream()
                .map(Cell::new)
                .collect(toSet());
    }

    public Set<Cell> getCells() {
        return new HashSet<>(cells);
    }

    public List<Cell> getUnsolvedCells() {
        return cells.stream()
                .filter(Cell::isEmpty)
                .toList();
    }

    public Cell getCellAt(int x, int y) {
        var cellOptional = cells.stream()
                .filter(p -> p.getPosition().row() == x && p.getPosition().column() == y)
                .findAny();
        if(cellOptional.isEmpty())
            throw new IllegalArgumentException("sudoku position is out of bounds: " + x + " " + y);
        return cellOptional.get();
    }

    private Set<CellGroup> getCellGroups(CellGroupType groupType) {
        return cells.stream()
                .collect(groupingBy(cell -> groupType.typeIndexOfPosition(cell.getPosition()), toSet()))
                .values()
                .stream()
                .map(groupedCells -> new CellGroup(groupedCells, groupType))
                .collect(toSet());
    }

    public Set<CellGroup> getAllCellGroups() {
        Set<CellGroup> groups = new HashSet<>();
        for (CellGroupType type : CellGroupType.values()) {
            groups.addAll(getCellGroups(type));
        }
        return groups;
    }

    public boolean isFilled() {
        return getUnsolvedCells().isEmpty();
    }
}
