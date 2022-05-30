package hwr.oop.riddler.logic;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellPosition;

import java.util.HashSet;
import java.util.Set;

public class SudokuBuilder {
    private final Set<Cell> cells = new HashSet<>();
    private final int sudokuSize;

    public SudokuBuilder(int sudokuSize) {
        this.sudokuSize = sudokuSize;
    }

    public void set(int value, int row, int column) {
        int boxIndex = calculateBoxIndex(row, column, sudokuSize);
        CellPosition position = new CellPosition(row, column, boxIndex);
        Cell cell = new Cell(value, position);
        cells.add(cell);
    }

    public Sudoku buildSudoku() {
        addEmptyCells();
        return new Sudoku(cells);
    }

    private void addEmptyCells() {
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {
                if(!hasCellAt(row, column)) {
                    set(0, row, column);
                }
            }
        }
    }

    private boolean hasCellAt(int row, int column) {
        return cells.stream().map(Cell::getPosition).anyMatch(position -> position.row() == row && position.column() == column);
    }

    private int calculateBoxIndex(int row, int column, int sudokuSize) {
        int boxSize = (int) Math.sqrt(sudokuSize);
        return (row / boxSize) * boxSize + (column / boxSize);
    }
}
