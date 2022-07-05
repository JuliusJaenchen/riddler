package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;
import hwr.oop.riddler.model.component.CellGroupType;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
                .collect(groupingBy(cell -> cell.getCellGroupIndicators().getCellGroupIndicator(cellGroupType), toSet()))
                .values()
                .stream()
                .map(CellGroup::new)
                .collect(toSet());
    }

    public Set<Cell> getCells() {
        return new HashSet<>(cells);
    }

    public Set<Cell> getUnsolvedCells() {
        return cells.stream()
                .filter(Cell::isEmpty)
                .collect(toSet());
    }

    public Cell getCellAt(int row, int column) {
        Optional<Cell> result = cells.stream()
                .filter(cell -> (cell.getCellGroupIndicators().row() == row) && (cell.getCellGroupIndicators().column() == column))
                .findAny();
        if (result.isEmpty())
            throw new IllegalStateException("no cell found at: " + row + ", " + column);
        return result.get();
    }

    public boolean isFilled() {
        return getUnsolvedCells().isEmpty();
    }
}
