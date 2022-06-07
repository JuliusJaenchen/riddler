package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;

public class BacktrackingComponent implements SolvingComponent {


    @Override
    public void execute(Sudoku sudoku) {
        Sudoku workingCopy = new Sudoku(sudoku);
    }

    @Override
    public boolean changesWereMade() {
        return false;
    }
}
