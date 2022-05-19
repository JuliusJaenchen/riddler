package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CellPositionTest {
    CellPosition position1 = new CellPosition(3, 3, 4);
    CellPosition position1copy = new CellPosition(3, 3, 4);
    CellPosition position2 = new CellPosition(1, 0, 0);

    @Test
    void position1_isEqualTo_position1copy() {
        assertEquals(position1, position1copy);
    }

    @Test
    void position1_isNotEqualTo_position2() {
        assertNotEquals(position1, position2);
    }

    @Test
    void getCellGroupIndex_row() {
        assertEquals(3, position1.getCellGroupIndex(CellGroupType.ROW));
    }

    @Test
    void getCellGroupIndex_column() {
        assertEquals(3, position1.getCellGroupIndex(CellGroupType.COLUMN));
    }

    @Test
    void getCellGroupIndex_box() {
        assertEquals(4, position1.getCellGroupIndex(CellGroupType.BOX));
    }
}
