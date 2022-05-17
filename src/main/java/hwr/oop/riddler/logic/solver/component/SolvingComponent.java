package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;

public interface SolvingComponent {
    void execute(Sudoku sudoku);
    boolean changesWereMade();
}
