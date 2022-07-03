package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.io.parser.SudokuParser;
import hwr.oop.riddler.logic.validator.SudokuValidator;
import hwr.oop.riddler.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSolverTest {
    SudokuParser parser = new SudokuParser();
    BacktrackingSolver solver = new BacktrackingSolver();
    SudokuValidator validator = new SudokuValidator();

    Sudoku solvedSudoku;

    @BeforeEach
    void setUp() {
        var unsolvedSudoku = parser.parse("src/test/resources/unsolvedSudokus/sudoku.1.txt");
        solvedSudoku = solver.getSolvedSudoku(unsolvedSudoku);
    }

    @Test
    void solvedSudokuIsValid() {
        assertTrue(isSolvedCorrectly(solvedSudoku));
    }

    private boolean isSolvedCorrectly(Sudoku sudoku) {
        return sudoku.isFilled() && validator.isValid(sudoku);
    }
}