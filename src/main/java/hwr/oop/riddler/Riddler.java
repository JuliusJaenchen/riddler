package hwr.oop.riddler;

import hwr.oop.riddler.io.RiddlerArguments;
import hwr.oop.riddler.io.SudokuParser;
import hwr.oop.riddler.io.SudokuPrinter;
import hwr.oop.riddler.logic.solver.BacktrackingSolver;
import hwr.oop.riddler.model.Sudoku;

import java.io.File;

public class Riddler {
    public static void main(String[] args) throws IllegalArgumentException {
        var arguments = new RiddlerArguments(args);

        var sudokuParser = new SudokuParser();
        var solver = new BacktrackingSolver();

        var sudoku = sudokuParser.parse(new File(arguments.sudokuFilePath()));

        Sudoku solvedSudoku = solver.solve(sudoku);

        var sudokuPrinter = new SudokuPrinter(System.out);
        sudokuPrinter.print(solvedSudoku);
    }
}
