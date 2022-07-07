package hwr.oop.riddler.logic.validator.constraints;

import hwr.oop.riddler.model.Sudoku;

public interface Constraint {
    boolean isSatisfiedBy(Sudoku object);
}
