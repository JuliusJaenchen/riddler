package hwr.oop.riddler.model;

import hwr.oop.riddler.io.SudokuParser;

import hwr.oop.riddler.model.component.CellGroup;
import hwr.oop.riddler.model.component.CellPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UnsolvedSudokuTest {
    Sudoku unsolvedSudoku;

    private final int[][] unsolvedFourByFourArray = {
            {0, 1, 0, 3},
            {3, 0, 1, 0},
            {0, 3, 0, 1},
            {0, 4, 0, 2},
    };

    @BeforeEach
    void setup() {
        SudokuParser parser = new SudokuParser();
        unsolvedSudoku = parser.parse(unsolvedFourByFourArray);
    }

    @Test
    void isFilled_unsolved_isNotFilled() {
        assertFalse(unsolvedSudoku.isFilled());
    }

    @Test
    void getCellAt_topLeftCornerPosition_isEmptyCell() {
        assertTrue(unsolvedSudoku.getCellAt(new CellPosition(0, 0, 0)).isEmpty());
    }

    @Test
    void getCellAt_topLeftCornerCoordinates_isEmptyCell() {
        assertTrue(unsolvedSudoku.getCellAt(0, 0).isEmpty());
    }

    @Test
    void getSize_isCorrectSize() {
        assertEquals(4, unsolvedSudoku.getSize());
    }

    @Test
    void getAllCellGroups_findsAllCellGroups() {
        Set<Set<Integer>> rows = Set.of(
                Set.of(1,3),
                Set.of(2,4)
        );
        Set<Set<Integer>> columns = Set.of(
                Set.of(3),
                Set.of(1,3,4),
                Set.of(1),
                Set.of(1,2,3)
        );
        Set<Set<Integer>> boxes = Set.of(
                Set.of(1,3),
                Set.of(3,4),
                Set.of(1,2)
        );
        Set<Set<Integer>> expectedGroups = new HashSet<>();
        expectedGroups.addAll(rows);
        expectedGroups.addAll(columns);
        expectedGroups.addAll(boxes);

        Set<Set<Integer>> testableGroups = unsolvedSudoku.getAllCellGroups()
                .stream()
                .map(CellGroup::getCellValues)
                .collect(Collectors.toSet());

        assertEquals(expectedGroups, testableGroups);
    }

    @Test
    void getCellAt_invalidPosition_throwsException() {
        CellPosition illegalPosition = new CellPosition(0, 0, 5);
        assertThrows(IllegalStateException.class, () -> unsolvedSudoku.getCellAt(illegalPosition));
    }

    @Test
    void getCellAt_invalidCoordinates_throwsException() {
        assertThrows(IllegalStateException.class, () -> unsolvedSudoku.getCellAt(5,0));
    }

    @Test
    void getCells_getsAllCells() {
        assertEquals(16, unsolvedSudoku.getCells().size());
    }
}
