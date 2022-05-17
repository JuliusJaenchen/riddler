package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.SudokuValidator;
import hwr.oop.riddler.logic.solver.component.PossiblesEliminator;
import hwr.oop.riddler.logic.solver.component.SinglePossibleImplementor;
import hwr.oop.riddler.logic.solver.component.SolvingComponent;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.Collections;
import java.util.HashSet;

public class BacktrackingSolver2 {
    private final SolvingComponent[] solvingComponents = {
            new PossiblesEliminator(),
            new SinglePossibleImplementor(),
    };

    private Sudoku workingCopy;

    public void solve(Sudoku sudoku) {
        workingCopy = new Sudoku(sudoku);

        var possibleCells = workingCopy.getUnsolvedCells();
        while (possibleCells.size() > 0) {
            var targetCell = possibleCells.get(0);
            assumeValue(targetCell);

            if (new SudokuValidator(workingCopy).isInvalid()) {
                targetCell.resetValue();
            }
            possibleCells = workingCopy.getUnsolvedCells();
        }
        backtrack(sudoku);
    }

    private void backtrack(Sudoku sudoku) {
        var unsolvedCells = sudoku.getUnsolvedCells();
        Collections.reverse(unsolvedCells);
        for (Cell cell : unsolvedCells) {
            var workingCopyCell = workingCopy.getCellAt(cell.getPosition().row(), cell.getPosition().column());
            if (workingCopyCell.getImpossibles().size() < 9) {
                workingCopyCell.resetValue();
                break;
            } else {
                workingCopyCell.resetValue();
                workingCopyCell.setImpossibles(new HashSet<>(cell.getImpossibles()));
            }
        }
    }


    private void assumeValue(Cell targetCell) {
        int assumedValue = getAPossibleValue(targetCell);
        targetCell.addImpossible(assumedValue);
        targetCell.setValue(assumedValue);
    }
    private int getAPossibleValue(Cell cell) {
        for (int value = 1; value <= workingCopy.getSize(); value++) {
            if (!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalStateException("empty cell has no possible values");
    }
}
