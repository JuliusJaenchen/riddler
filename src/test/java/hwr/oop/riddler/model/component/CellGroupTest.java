package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellGroupTest {
    CellGroup cellGroup;
    Set<Cell> cells;

    @BeforeEach
    private void setup() {
        cells = Set.of(
                new Cell(new CellPosition(0, 0, 0)),
                new Cell(new CellPosition(1, 1, 0)),
                new Cell(3, new CellPosition(2, 2, 0)),
                new Cell(4, new CellPosition(3, 3, 4))
        );
        cellGroup = new CellGroup(cells);
    }

    @Test
    void cellGroup_getCells() {
        assertEquals(cells, cellGroup.cells());
    }

    @Test
    void cellGroup_getUnsolvedCells() {
        Set<Cell> unsolvedCells = cells.stream().filter(Cell::isEmpty).collect(Collectors.toSet());
        assertEquals(unsolvedCells, cellGroup.getUnsolvedCells());
    }

    @Test
    void cellGroup_getAllValues() {
        assertEquals(Set.of(3, 4), cellGroup.getCellValues());
    }

    @Test
    void cellGroup_equalCellGroupsAreEqual() {
        var cellGroup2 = new CellGroup(cells);
        assertEquals(cellGroup, cellGroup2);
    }

    @Test
    void cellGroup_hashCode() {
        int hashcode = cellGroup.hashCode();
        assertEquals(hashcode, cellGroup.hashCode());
    }
}
