package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

public class SinglePossibleImplementer implements SolvingComponent {
    private Sudoku sudoku;
    private boolean changesWereMade;

    @Override
    public void execute(Sudoku sudoku) {
        this.sudoku = sudoku;
        changesWereMade = false;

        this.sudoku.getCells().forEach(this::fillCellWithOnlyPossibleValue);
    }

    @Override
    public boolean changesWereMade() {
        return changesWereMade;
    }

    private void fillCellWithOnlyPossibleValue(Cell cell) {
        if (cell.isEmpty() && cellHasOnePossibleValue(cell)) {
            cell.setValue(onlyPossibleValue(cell));
            changesWereMade = true;
        }
    }

    private boolean cellHasOnePossibleValue(Cell cell) {
        return sudoku.getSize() - cell.getImpossibleValues().size() == 1;
    }

    private int onlyPossibleValue(Cell cell) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if (!cell.getImpossibleValues().contains(value))
                return value;
        }
        //This exception can never be reached, since this state is caught by cellHasOnePossibleValue().
        throw new IllegalArgumentException("cell content has no possible value");
    }
}
