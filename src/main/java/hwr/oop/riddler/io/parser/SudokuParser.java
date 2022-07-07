package hwr.oop.riddler.io.parser;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.builder.CellCoordinate;
import hwr.oop.riddler.model.builder.SudokuBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class SudokuParser {
    public Sudoku parse(String sudokuFilePathname) {
        return this.parse(new File(sudokuFilePathname));
    }

    public Sudoku parse(File inputFile) {
        var sudokuArray = readArrayFromFile(inputFile);
        return parseArray(sudokuArray);
    }

    private int[][] readArrayFromFile(File inputFile) {
        try (var sudokuFileReader = new SudokuFileReader(inputFile)) {
            return sudokuFileReader.lines()
                    .map(this::lineToIntArray)
                    .toArray(int[][]::new);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not parse input file " + inputFile.getName(), e);
        }
    }

    private int[] lineToIntArray(String line) {
        return line.chars()
                .map(Character::getNumericValue)
                .toArray();
    }

    private Sudoku parseArray(int[][] sudoku) {
        SudokuBuilder builder = new SudokuBuilder(sudoku.length);
        int sudokuSize = sudoku.length;
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {
                builder.fillCell(new CellCoordinate(row, column), sudoku[row][column]);
            }
        }
        return builder.build();
    }
}
