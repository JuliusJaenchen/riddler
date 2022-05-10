package hwr.oop.riddler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    Sudoku unsolved;
    Sudoku solved;
    Sudoku unsolvedNineByNine;
    private final int[][] unsolvedNineByNineArray = {
            {0, 0, 0, 9, 0, 0, 4, 0, 0},
            {5, 0, 6, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 3, 0, 0, 6, 8, 1},

            {0, 2, 0, 0, 5, 0, 1, 0, 0},
            {0, 8, 0, 0, 7, 9, 0, 0, 0},
            {6, 0, 7, 0, 0, 8, 0, 0, 0},

            {0, 0, 0, 0, 2, 0, 0, 4, 0},
            {0, 0, 0, 0, 9, 0, 0, 5, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 2},
    };

    private final int[][] unsolvedFourByFourArray = {
            {0, 1, 0, 3},
            {3, 0, 1, 0},
            {0, 3, 0, 1},
            {0, 4, 0, 2},
    };

    private final int[][] solvedFourByFourArray = {
            {4, 1, 2, 3},
            {3, 2, 1, 4},
            {2, 3, 4, 1},
            {1, 4, 3, 2},
    };

    @BeforeEach
    void setup() {
        unsolved = new Sudoku(unsolvedFourByFourArray);
        solved = new Sudoku(solvedFourByFourArray);
        unsolvedNineByNine = new Sudoku(unsolvedNineByNineArray);
    }

    @Test
    void unsolved_isFilled_isFalse() {
        assertFalse(unsolved.isFilled());
    }

    @Test
    void solved_isFilled() {
        assertTrue(solved.isFilled());
    }

    @Test
    void unsolved_getValues() {
        assertTrue(Arrays.deepEquals(unsolvedFourByFourArray, unsolved.getValues()));
    }

    @Test
    void solved_getValues() {
        assertTrue(Arrays.deepEquals(solvedFourByFourArray, solved.getValues()));
    }

    @Test
    void unsolved_getCells() {
        var cells = new ArrayList<Integer>();
        for (int[] row : unsolvedFourByFourArray) {
            for (int value : row) {
                cells.add(value);
            }
        }
        assertEquals(cells, unsolved.getCells().stream().map(c -> c.isFilled() ? c.getValue() : 0).toList());
    }

    @Test
    void unsolved_getRow() {
        assertEquals(Set.of(1, 3), unsolved.getRow(0).getAllValues());
    }

    @Test
    void unsolved_getColumn() {
        assertEquals(Set.of(3), unsolved.getColumn(0).getAllValues());
    }

    @Test
    void unsolved_getBox() {
        assertEquals(Set.of(1, 3), unsolved.getBox(0).getAllValues());
    }

    @Test
    void unsolved_getRows() {
        assertEquals(4, unsolved.getRows().size());
    }

    @Test
    void unsolved_getColumns() {
        assertEquals(4, unsolved.getColumns().size());
    }

    @Test
    void unsolved_getBoxes() {
        assertEquals(4, unsolved.getBoxes().size());
    }

    @Test
    void unsolved_getConcatinatedCellGroups() {
        assertEquals(3 * 4, unsolved.getConcatenatedCellGroups().size());
    }

    @Test
    void unsolved_canBeCopied() {
        assertTrue(Arrays.deepEquals(unsolved.getValues(), new Sudoku(unsolved).getValues()));
    }

    @Test
    void unsolved_getSize() {
        assertEquals(4, unsolved.getSize());
    }
}
