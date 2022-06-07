package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.solver.component.PossiblesEliminator;
import hwr.oop.riddler.logic.solver.component.SinglePossibleImplementor;
import hwr.oop.riddler.logic.solver.component.SolvingComponent;
import hwr.oop.riddler.model.Sudoku;

import java.util.List;

public class SudokuSolver {
    private final Sudoku sudoku;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public void solve() {
        List<SolvingComponent> componentList = List.of(new PossiblesEliminator(), new SinglePossibleImplementor());

    }
}
