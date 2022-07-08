package hwr.oop.riddler;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiddlerTest {
    @Test
    void main_calledWithSudokuAsArgument_printsSolvedSudokuToSystemOut() throws IOException {

        var pipeInput = new PipedInputStream();
        var out = new BufferedOutputStream(new PipedOutputStream(pipeInput));

        System.setOut(new PrintStream(out));
        Riddler.main(new String[]{"src/test/resources/unsolvedSudokus/sudoku.1.txt"});

        var resultReader = new BufferedReader(new InputStreamReader(pipeInput));
        var result = resultReader.lines().map(String::trim);

        var expectedResultReader = new BufferedReader(new FileReader("src/test/resources/solvedSudokus/sudoku.1.txt"));

        assertEquals(expectedResultReader.lines().toList(), result.toList());
    }

}
