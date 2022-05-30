package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.UnfilledCellContent;

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
        if (cell.getContent() instanceof UnfilledCellContent content) {
            if (cellHasOnePossibleValue(content)) {
                cell.setValue(onlyPossibleValue(content));
                changesWereMade = true;
            }
        }
    }

    private boolean cellHasOnePossibleValue(UnfilledCellContent content) {
        return sudoku.getSize() - content.getImpossibles().size() == 1;
    }

    private int onlyPossibleValue(UnfilledCellContent content) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if (!content.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalArgumentException("cellcontent has no possible value");
    }
}
