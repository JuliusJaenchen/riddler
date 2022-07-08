package hwr.oop.riddler.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiddlerArgumentsTest {
    @Test
    void constructor_WrongAmountOfArguments_InvalidArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{"Too Many", "Arguments"}));
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{}));
    }

    @Test
    void sudokuFilePath_StateWithPath_ReturnsSamePath() {
        var path = "someCorrectFilepath.sudoku";
        RiddlerArguments arguments = new RiddlerArguments(new String[]{path});
        assertEquals(path, arguments.sudokuFilePath());
    }
}
