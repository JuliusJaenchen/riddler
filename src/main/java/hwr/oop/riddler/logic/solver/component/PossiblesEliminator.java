package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

public class PossiblesEliminator implements SolvingComponent {
    boolean changesWereMade;

    @Override
    public void execute(Sudoku sudoku) {
        this.changesWereMade = false;

        for (CellGroup cellGroup : sudoku.getAllCellGroups()) {
            removePossibleCellValues(cellGroup);
        }
    }

    @Override
    public boolean changesWereMade() {
        return changesWereMade;
    }

    private void removePossibleCellValues(CellGroup cellGroup) {
        for (Cell cell : cellGroup.getUnsolvedCells()) {
            boolean addedImpossibles = cell.addImpossibles(cellGroup.getCellValues());
            if (addedImpossibles)
                this.changesWereMade = true;
        }
    }
}
