package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

public class SinglePossibleImplementor implements SolvingComponent {
    private Sudoku sudoku;
    private boolean changesWereMade;

    @Override
    public void execute(Sudoku sudoku) {
        this.sudoku = sudoku;
        changesWereMade = false;

        this.sudoku.getCells().forEach(this::fillCellWithOnlyPossibleValue);

        for (Cell cell : this.sudoku.getCells()) {
            fillCellWithOnlyPossibleValue(cell);
        }
    }

    @Override
    public boolean changesWereMade() {
        return changesWereMade;
    }

    private void fillCellWithOnlyPossibleValue(Cell cell) {
        if (cell.isFilled() || !cellHasOnePossibleValue(cell))
            return;

        cell.setValue(onlyPossibleValue(cell));
        changesWereMade = true;
    }

    private boolean cellHasOnePossibleValue(Cell cell) {
        return sudoku.getSize() - cell.getImpossibles().size() == 1;
    }

    private int onlyPossibleValue(Cell cell) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if (!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalArgumentException("cell has no possible value");
    }
}
