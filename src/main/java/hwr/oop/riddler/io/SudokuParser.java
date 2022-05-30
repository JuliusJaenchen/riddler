package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.logic.SudokuBuilder;

import java.io.*;

public class SudokuParser {
    public Sudoku parse(File inputFile) {
        var sudokuArray = readArrayFromFile(inputFile);
        return parseArray(sudokuArray);
    }

    public Sudoku parse(int[][] sudokuArray) {
        return parseArray(sudokuArray);
    }

    private Sudoku parseArray(int[][] sudoku) {
        SudokuBuilder builder = new SudokuBuilder(sudoku.length);
        int sudokuSize = sudoku.length;
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {
                builder.set(sudoku[row][column], row, column);
            }
        }
        return builder.buildSudoku();
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
}
