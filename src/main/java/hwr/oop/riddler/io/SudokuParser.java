package hwr.oop.riddler.io;

import hwr.oop.riddler.io.model.CellCoordinate;
import hwr.oop.riddler.io.model.LineValue;
import hwr.oop.riddler.io.model.SudokuBuilder;
import hwr.oop.riddler.io.model.SudokuFileLine;
import hwr.oop.riddler.model.Sudoku;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class SudokuParser {
    public Sudoku parse(File inputFile) {
        var sudokuLines = readSudokuLinesFromFile(inputFile);
        return parseLines(sudokuLines);
    }

    private List<SudokuFileLine> readSudokuLinesFromFile(File inputFile) {
        try (var sudokuFileReader = new SudokuFileReader(inputFile)) {
            return sudokuFileReader.lines()
                    .map(SudokuFileLine::fromString)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not parse input file " + inputFile.getName(), e);
        }
    }

    private Sudoku parseLines(List<SudokuFileLine> lines) {
        int sudokuSize = lines.size();

        SudokuBuilder builder = new SudokuBuilder(sudokuSize);
        for (int row = 0; row < sudokuSize; row++) {
            SudokuFileLine line = lines.get(row);
            for (LineValue value : line.values()) {
                var coordinate = new CellCoordinate(row, value.position());
                builder.fillCell(coordinate, value.numericValue());
            }
        }
        return builder.build();
    }
}
