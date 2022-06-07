package hwr.oop.riddler.logic.validator.constraints.cell.content;

import hwr.oop.riddler.logic.validator.constraints.Constraint;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

public class UnfilledCellsHavePossiblesConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Sudoku sudoku) {
        return sudoku.getCells()
                .stream()
                .filter(Cell::isEmpty)
                .allMatch(cell -> cell.getImpossibles().size() < sudoku.getSize());
    }
}
