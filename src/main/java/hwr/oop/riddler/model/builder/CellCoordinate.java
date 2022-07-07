package hwr.oop.riddler.model.builder;

import hwr.oop.riddler.model.component.CellGroupIndicators;

public record CellCoordinate(int row, int column) {
    public CellGroupIndicators toCellGroupIndicators(int sudokuSize) {
        int boxIndex = calculateBoxIndex(sudokuSize);
        return new CellGroupIndicators(row, column, boxIndex);
    }

    private int calculateBoxIndex(int sudokuSize) {
        int boxSize = (int) Math.sqrt(sudokuSize);
        return (row / boxSize) * boxSize + (column / boxSize);
    }
}
