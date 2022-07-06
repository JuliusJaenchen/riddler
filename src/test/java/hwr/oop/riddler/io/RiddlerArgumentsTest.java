package hwr.oop.riddler.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RiddlerArgumentsTest {
    @Test
    public void constructor_WrongAmountOfArguments_InvalidArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{"Too Many", "Arguments"}));
        assertThrows(IllegalArgumentException.class, () -> new RiddlerArguments(new String[]{}));
    }

    @Test
    public void sudokoFilePath_StateWithPath_ReturnsSamePath() {
        var path = "someCorrectFilepath.sudoku";
        RiddlerArguments arguments = new RiddlerArguments(new String[]{path});
        assertEquals(path, arguments.sudokuFilePath());
    }
}
