package hwr.oop.riddler.model;

import hwr.oop.riddler.io.parser.SudokuParser;
import hwr.oop.riddler.model.component.CellGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UnsolvedSudokuTest {
    SudokuParser parser = new SudokuParser();
    Sudoku unsolvedSudoku;
    Sudoku smallUnsolvedSudoku;

    @BeforeEach
    void setup() {
        unsolvedSudoku = parser.parse("src/test/resources/unsolvedSudokus/sudoku.1.txt");
        smallUnsolvedSudoku = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
    }

    @Test
    void isFilled_unsolved_isNotFilled() {
        assertFalse(unsolvedSudoku.isFilled());
    }

    @Test
    void getCellAt_topLeftCornerCoordinates_isEmptyCell() {
        assertTrue(unsolvedSudoku.getCellAt(0, 0).isEmpty());
    }

    @Test
    void getSize_isCorrectSize() {
        assertEquals(9, unsolvedSudoku.getSize());
    }

    @Test
    void getAllCellGroups_findsAllCellGroups() {
        Set<Set<Integer>> rows = Set.of(
                Set.of(1, 3),
                Set.of(1, 3, 4),
                Set.of(1, 2, 4),
                Set.of(2, 3)
        );
        Set<Set<Integer>> columns = Set.of(
                Set.of(1, 2, 4),
                Set.of(3, 4),
                Set.of(1, 3),
                Set.of(1, 2, 3)
        );
        Set<Set<Integer>> boxes = Set.of(
                Set.of(1, 3, 4),
                Set.of(1, 3),
                Set.of(2, 4),
                Set.of(1, 2, 3)
        );
        Set<Set<Integer>> expectedGroups = new HashSet<>();
        expectedGroups.addAll(rows);
        expectedGroups.addAll(columns);
        expectedGroups.addAll(boxes);

        Set<Set<Integer>> testableGroups = smallUnsolvedSudoku.getAllCellGroups()
                .stream()
                .map(CellGroup::getCellValues)
                .collect(Collectors.toSet());

        assertEquals(expectedGroups, testableGroups);
    }


    @Test
    void getCellAt_invalidCoordinates_throwsException() {
        assertThrows(IllegalStateException.class, () -> unsolvedSudoku.getCellAt(10, 0));
    }

    @Test
    void getCells_getsAllCells() {
        assertEquals(81, unsolvedSudoku.getCells().size());
    }

    @Test
    void copyConstructor_createsSudokuWithSameSize() {
        var copy = new Sudoku(unsolvedSudoku);
        assertEquals(unsolvedSudoku.getSize(), copy.getSize());
    }
}
