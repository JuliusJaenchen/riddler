package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroupIndices;

import java.util.HashSet;
import java.util.Set;

public class SudokuBuilder {
    private final Set<Cell> cells = new HashSet<>();
    private final int sudokuSize;

    public SudokuBuilder(int sudokuSize) {
        this.sudokuSize = sudokuSize;
    }

    public SudokuBuilder fillCell(CellCoordinate coordinate, int value) {
        CellGroupIndices position = coordinate.toCellIndices(sudokuSize);
        Cell cell = new Cell(value, position);
        cells.add(cell);
        return this;
    }

    public Sudoku build() {
        addEmptyCells();
        return new Sudoku(cells);
    }

    private void addEmptyCells() {
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {
                if(!hasCellAt(row, column)) {
                    fillCell(new CellCoordinate(row, column), 0);
                }
            }
        }
    }

    private boolean hasCellAt(int row, int column) {
        return cells.stream()
                .map(Cell::getCellGroupIndices)
                .anyMatch(position -> position.row() == row && position.column() == column);
    }
}
