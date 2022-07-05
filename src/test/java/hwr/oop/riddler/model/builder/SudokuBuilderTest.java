package hwr.oop.riddler.model.builder;

import hwr.oop.riddler.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBuilderTest {
    SudokuBuilder builder;

    @BeforeEach
    void setup() {
        builder = new SudokuBuilder(4);
    }

    @Test
    void builder_AutocompletesEmptyCells() {
        Sudoku result = builder
                .fillCell(new CellCoordinate(0, 0), 1)
                .build();

        assertEquals(16, result.getCells().size());
    }
}
