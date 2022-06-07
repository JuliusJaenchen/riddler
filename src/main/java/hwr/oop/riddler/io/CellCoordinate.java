package hwr.oop.riddler.io;

import hwr.oop.riddler.model.component.CellGroupIndices;

record CellCoordinate(int row, int column) {
    public CellGroupIndices toCellIndices(int sudokuSize) {
        int boxIndex = calculateBoxIndex(sudokuSize);
        return new CellGroupIndices(row, column, boxIndex);
    }

    private int calculateBoxIndex(int sudokuSize) {
        int boxSize = (int) Math.sqrt(sudokuSize);
        return (row / boxSize) * boxSize + (column / boxSize);
    }
}
