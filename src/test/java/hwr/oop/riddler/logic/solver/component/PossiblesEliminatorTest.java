package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.io.parser.SudokuParser;
import hwr.oop.riddler.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PossiblesEliminatorTest {
    PossiblesEliminator possiblesEliminator = new PossiblesEliminator();
    SudokuParser parser = new SudokuParser();

    Sudoku sudokuSubject;

    @BeforeEach
    void setup() {
        sudokuSubject = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
        possiblesEliminator.execute(sudokuSubject);
    }

    @Test
    void addsAllImpossibleValuesToCells() {
        var emptyCell = sudokuSubject.getCellAt(0, 1);
        assertEquals(Set.of(1, 3, 4), emptyCell.getImpossibleValues());
    }

    @Test
    void registersThatChangesWereMade() {
        assertTrue(possiblesEliminator.changesWereMade());
    }

    @Test
    void registersThatNoChangesWereMade() {
        possiblesEliminator.execute(sudokuSubject);
        assertFalse(possiblesEliminator.changesWereMade());
    }
}