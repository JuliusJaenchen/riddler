package hwr.oop.riddler.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiddlerArgumentsTest {
    @Test
    void constructor_wrongAmountOfArguments_invalidArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{"Too Many", "Arguments"}));
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{}));
    }

    @Test
    void sudokuFilePath_stateWithPath_returnsSamePath() {
        var path = "someCorrectFilepath.sudoku";
        RiddlerArguments arguments = new RiddlerArguments(new String[]{path});
        assertEquals(path, arguments.sudokuFilePath());
    }
}
