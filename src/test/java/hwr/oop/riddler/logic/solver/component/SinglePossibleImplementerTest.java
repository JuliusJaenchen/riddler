package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.io.parser.SudokuParser;
import hwr.oop.riddler.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SinglePossibleImplementerTest {
    SudokuParser parser = new SudokuParser();
    PossiblesEliminator possiblesEliminator = new PossiblesEliminator();
    SinglePossibleImplementer singlePossibleImplementer = new SinglePossibleImplementer();

    Sudoku sudokuSubject;

    @BeforeEach
    void setup() {
        sudokuSubject = parser.parse("src/test/resources/smallUnsolvedSudoku.txt");
        possiblesEliminator.execute(sudokuSubject);
        singlePossibleImplementer.execute(sudokuSubject);
    }

    @Test
    void solvesSimpleSudokuWhereEachCellHasOnlyOnePossibleValue() {
        assertTrue(sudokuSubject.isFilled());
    }

    @Test
    void registersThatChangesWereMade() {
        assertTrue(singlePossibleImplementer.changesWereMade());
    }

    @Test
    void registersThatNoChangesWereMade() {
        singlePossibleImplementer.execute(sudokuSubject);
        assertFalse(singlePossibleImplementer.changesWereMade());
    }
}