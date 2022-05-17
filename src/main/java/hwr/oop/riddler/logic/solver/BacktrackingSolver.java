package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.SudokuValidator;
import hwr.oop.riddler.logic.solver.component.*;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BacktrackingSolver {
    private final SolvingComponent[] solvingComponents = {
            new PossiblesEliminator(),
            //new AdvancedPossiblesEliminator(), -- TODO: work on this once solver works
            new SinglePossibleImplementor(),
    };

    private Sudoku workingCopy;
    private final Deque<Sudoku> sudokuBackups = new ArrayDeque<>();

    public Sudoku solve(Sudoku sudoku) {
        workingCopy = new Sudoku(sudoku);

        while (true) {
            boolean changesWereMade = solveWithSteps();
            if (!changesWereMade)
                break;
        }

        if (!new SudokuValidator(sudoku).isValid()) {
            backtrack();
            workingCopy = solve(workingCopy);
        }

        if (!workingCopy.isFilled()) {
            assumeValue();
            workingCopy = solve(workingCopy);
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
        Cell targetCell = getNextUnsolvedCell();
        int assumedValue = getAPossibleValue(targetCell);

        targetCell.addImpossible(assumedValue);
        sudokuBackups.push(new Sudoku(workingCopy));

        targetCell.setValue(assumedValue);
    }

    private Cell getNextUnsolvedCell() {
        List<Cell> unsolvedCells = workingCopy.getUnsolvedCells();
        return unsolvedCells.get(0);
    }

    private int getAPossibleValue(Cell cell) {
        for (int value = 1; value <= workingCopy.getSize(); value++) {
            if (!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalStateException("empty cell has no possible values");
    }
}
