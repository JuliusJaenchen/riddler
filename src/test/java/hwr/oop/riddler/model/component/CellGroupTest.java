package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellGroupTest {
    CellGroup cellGroup;
    Set<Cell> cells;

    @BeforeEach
    private void setup() {
        cells = Set.of(
                new Cell(new CellGroupIndices(0, 0, 0)),
                new Cell(new CellGroupIndices(1, 1, 0)),
                new Cell(3, new CellGroupIndices(2, 2, 0)),
                new Cell(4, new CellGroupIndices(3, 3, 4))
        );
        cellGroup = new CellGroup(cells);
    }

    @Test
    void cellGroup_getCells() {
        assertEquals(cells, cellGroup.cells());
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
