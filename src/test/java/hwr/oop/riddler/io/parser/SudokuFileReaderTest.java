package hwr.oop.riddler.io.parser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFileReaderTest {
    @Test
    void constructor_unknownFile_fileNotFoundExceptionIsThrown() {
        assertThrows(FileNotFoundException.class, () -> new SudokuFileReader(new File("thisfiledoesnotexist")));
    }

    @Test
    void lines_inputWithEmptyLines_emptyLinesGetRemoved() {
        var input = new File("src/test/resources/hwr/oop/riddler/io/SudokuFileReader/emptylines");
        try (var reader = new SudokuFileReader(input)) {
            var result = reader.lines();
            assertEquals(6, result.count());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void lines_inputWithEmptyLines_remainingLinesAreNotEmpty() {
        var input = new File("src/test/resources/hwr/oop/riddler/io/SudokuFileReader/emptylines");
        try (var reader = new SudokuFileReader(input)) {
            var result = reader.lines();
            assertFalse(result.anyMatch(String::isEmpty));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void lines_inputWithUnsanitizedLines_emptySpacesAreRemoved() {
        var input = new File("src/test/resources/hwr/oop/riddler/io/SudokuFileReader/unsanitized");
        try (var reader = new SudokuFileReader(input)) {
            var result = reader.lines();
            assertTrue(result.toList().contains("thiscontainsemptyspaces"));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void lines_inputWithUnsanitizedLines_underscoreGetsReplaced() {
        var input = new File("src/test/resources/hwr/oop/riddler/io/SudokuFileReader/unsanitized");
        try (var reader = new SudokuFileReader(input)) {
            var result = reader.lines();
            assertTrue(result.toList().contains("this0standsforsomething0"));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void close_readerIsClosed_readerGetsClosed() {
        var input = new File("src/test/resources/hwr/oop/riddler/io/SudokuFileReader/emptylines");
        assertThrows(UncheckedIOException.class, () -> {
            try {
                var reader = new SudokuFileReader(input);
                reader.close();
                //sample action that interacts with the stream
                reader.lines().count();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
