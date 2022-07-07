package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.solver.component.PossiblesEliminator;
import hwr.oop.riddler.logic.solver.component.SinglePossibleImplementer;
import hwr.oop.riddler.logic.solver.component.SolvingComponent;
import hwr.oop.riddler.logic.validator.SudokuValidator;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

public class BacktrackingSolver {
    private final SudokuValidator validator = new SudokuValidator();
    private final SolvingComponent[] solvingComponents = {
            new PossiblesEliminator(),
            new SinglePossibleImplementer(),
    };

    private Sudoku workingCopy;
    private final Deque<Sudoku> sudokuBackups = new ArrayDeque<>();

    public Sudoku getSolvedSudoku(Sudoku sudoku) {
        workingCopy = new Sudoku(sudoku);

        while (true) {
            boolean changesWereMade = solveWithSteps();
            if (!changesWereMade)
                break;
        }

        if (!validator.isValid(workingCopy)) {
            backtrack();
            workingCopy = getSolvedSudoku(workingCopy);
        }

        if (!workingCopy.isFilled()) {
            assumeValue();
            workingCopy = getSolvedSudoku(workingCopy);
        }

        return workingCopy;
    }

    private boolean solveWithSteps() {
        for (SolvingComponent solvingComponent : solvingComponents) {
            solvingComponent.execute(workingCopy);
            if (solvingComponent.changesWereMade())
                return true;
        }
        return false;
    }

    private void backtrack() {
        workingCopy = sudokuBackups.pop();
    }

    private void assumeValue() {
        Cell unsolvedCell = getNextUnsolvedCell();
        int assumedValue = getAPossibleValue(unsolvedCell);

        unsolvedCell.addImpossibleValue(assumedValue);
        sudokuBackups.push(new Sudoku(workingCopy));

        unsolvedCell.setValue(assumedValue);
    }

    private Cell getNextUnsolvedCell() {
        Set<Cell> unsolvedCells = workingCopy.getUnsolvedCells();
        return unsolvedCells.iterator().next();
    }

    private int getAPossibleValue(Cell cell) {
        if (cell.isEmpty()) {
            for (int value = 1; value <= workingCopy.getSize(); value++) {
                if (!cell.getImpossibleValues().contains(value))
                    return value;
            }
        } else {
            throw new IllegalArgumentException("cell is already filled");
        }
        throw new IllegalStateException("cell has no possible values");
    }
}
