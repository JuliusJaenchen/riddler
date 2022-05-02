package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.*;

import java.util.*;
import java.util.function.IntFunction;

public class Sudoku {
    private final Cell[][] cells;
    private final int size;
    private final int boxSize;

    public Sudoku(int[][] input) {
        size = input.length;
        boxSize = (int) Math.sqrt(size);
        cells = new Cell[size][size];
        fillCells(input);
    }

    private void fillCells(int[][] input) {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int[] row = input[rowIndex];
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                int cellValue = row[columnIndex];
                Cell cell;
                if (cellValue != 0) {
                    cell = new Cell(cellValue);
                } else {
                    cell = new Cell();
                }
                cells[rowIndex][columnIndex] = cell;
            }
        }
    }

    public List<Cell> getAllCells() {
        var collectedCells = new ArrayList<Cell>();
        for (Cell[] rows : cells) {
            Collections.addAll(collectedCells, rows);
        }
        return collectedCells;
    }

    public CellGroup getRow(int rowIndex) {
        return new CellGroup(List.of(cells[rowIndex]));
    }

    public CellGroup getColumn(int columnIndex) {
        var column = new ArrayList<Cell>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            column.add(this.cells[rowIndex][columnIndex]);
        }
        return new CellGroup(column);
    }

    public CellGroup getBox(int boxIndex) {
        var boxCells = new ArrayList<Cell>(9);
        int boxLatitude = boxIndex / boxSize;
        int boxLongitude = boxIndex % boxSize;
        for (int y = 0; y < boxSize; y++) {
            for (int x = 0; x < boxSize; x++) {
                boxCells.add(cells[boxLatitude * boxSize + y][boxLongitude * boxSize + x]);
            }
        }
        return new CellGroup(boxCells);
    }

    public Set<CellGroup> getRows() {
        return getAllGroupsOfSameType(this::getRow);
    }

    public Set<CellGroup> getColumns() {
        return getAllGroupsOfSameType(this::getColumn);
    }

    public Set<CellGroup> getBoxes() {
        return getAllGroupsOfSameType(this::getBox);
    }

    private Set<CellGroup> getAllGroupsOfSameType(IntFunction<CellGroup> getGroup) {
        var groups = new HashSet<CellGroup>(size);
        for (int i = 0; i < size; i++) {
            groups.add(getGroup.apply(i));
        }
        return groups;
    }

    public Set<CellGroup> getAllCellGroups() {
        var allCellGroups = new HashSet<CellGroup>(size*3);
        allCellGroups.addAll(getRows());
        allCellGroups.addAll(getColumns());
        allCellGroups.addAll(getBoxes());
        return allCellGroups;
    }

    public Cell getCellAt(int row, int column) {
        return cells[row][column];
    }
}
