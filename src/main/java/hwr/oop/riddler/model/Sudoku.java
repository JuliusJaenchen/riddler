package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.*;
import lombok.Getter;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Sudoku {
    private final Set<Cell> cells;
    @Getter
    private final int size;

    public Sudoku(Set<Cell> cells) {
        this.cells = cells;
        size = (int) Math.sqrt(cells.size());
    }

    public Sudoku(Sudoku sudoku) {
        this.cells = sudoku.cells
                .stream()
                .map(Cell::new)
                .collect(toSet());
        this.size = sudoku.size;
    }

    public Set<CellGroup> getAllCellGroups() {
        var cellGroups = new HashSet<CellGroup>();
        for (CellGroupType type : CellGroupType.values()) {
            cellGroups.addAll(getAllCellGroupsOfSameType(type));
        }
        return cellGroups;
    }

    private Set<CellGroup> getAllCellGroupsOfSameType(CellGroupType cellGroupType) {
        return cells.stream()
                .collect(groupingBy(cell -> cell.getPosition().getCellGroupIndex(cellGroupType), toSet()))
                .values()
                .stream()
                .map(CellGroup::new)
                .collect(toSet());
    }

    public Set<Cell> getCells() {
        return new HashSet<>(cells);
    }

    public Set<Cell> getUnsolvedCells() {
        return cells.stream().filter(Cell::isEmpty).collect(toSet());
    }

    public Cell getCellAt(CellPosition position) {
        var optionalCell = cells.stream()
                .filter(cell -> cell.getPosition().equals(position))
                .findAny();
        if (optionalCell.isEmpty())
            throw new IllegalStateException("no cell found at: row:" + position.row() + " column:" + position.column());
        return optionalCell.get();
    }

    public Cell getCellAt(int row, int column) {
        return cells.stream()
                .filter(cell -> (cell.getPosition().row() == row) && (cell.getPosition().column() == column))
                .toList()
                .get(0);
    }

    public boolean isFilled() {
        return getUnsolvedCells().isEmpty();
    }
}
