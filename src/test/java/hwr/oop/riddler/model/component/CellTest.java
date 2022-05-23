package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell filledCell;
    private Cell emptyCell;

    @BeforeEach
    void setup() {
        filledCell = new Cell(2, new CellPosition(0, 0, 0));
        emptyCell = new Cell(new CellPosition(1, 1, 0));
    }

    @Test
    void emptyCell_isEmpty() {
        assertTrue(emptyCell.isEmpty());
    }

    @Test
    void emptyCell_isNotFilled() {
        assertFalse(emptyCell.isFilled());
    }

    @Test
    void emptyCell_setValueZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> emptyCell.setValue(0));
    }

    @Test
    void emptyCell_getValueFails() {
        assertThrows(IllegalStateException.class, () -> emptyCell.getValue());
    }

    @Test
    void emptyCell_assumesValue() {
        emptyCell.setValue(2);
        assertEquals(2, emptyCell.getValue());
    }

    @Test
    void emptyCell_addImpossible() {
        emptyCell.addImpossible(1);
        assertEquals(Set.of(1), emptyCell.getImpossibles());
    }

    @Test
    void emptyCell_addImpossibleRetrunsTrue() {
        assertTrue(emptyCell.addImpossible(1));
    }

    @Test
    void emptyCell_addDuplicateImpossibleReturnsFalse() {
        emptyCell.addImpossible(1);
        assertFalse(emptyCell.addImpossible(1));
    }

    @Test
    void emptyCell_addImpossibles() {
        emptyCell.addImpossibles(Set.of(1, 2));
        assertEquals(Set.of(1, 2), emptyCell.getImpossibles());
    }

    @Test
    void emptyCell_canBeCopied() {
        emptyCell.addImpossibles(Set.of(1, 3, 4));
        var copy = new Cell(emptyCell);
        assertEquals(Set.of(1, 3, 4), copy.getImpossibles());
    }

    @Test
    void filledCell_getValue() {
        assertEquals(2, filledCell.getValue());
    }

    @Test
    void filledCell_setValueFails() {
        assertThrows(IllegalStateException.class, () -> filledCell.setValue(1));
    }

    @Test
    void filledCell_isFilled() {
        assertTrue(filledCell.isFilled());
    }

    @Test
    void filledCell_isNotEmpty() {
        assertFalse(filledCell.isEmpty());
    }

    @Test
    void filledCell_canBeCopied() {
        var copiedCell = new Cell(filledCell);
        assertEquals(2, copiedCell.getValue());
    }

    @Test
    void emptyCell_setImpossible_zero() {
        assertThrows(IllegalArgumentException.class, () -> emptyCell.addImpossible(0));
    }

    @Test
    void emptyCell_getPosition() {
        assertEquals(new CellPosition(1, 1, 0), emptyCell.getPosition());
    }
}
