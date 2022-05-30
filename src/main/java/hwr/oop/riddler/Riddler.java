package hwr.oop.riddler;

import hwr.oop.riddler.io.RiddlerArgumentParser;
import hwr.oop.riddler.io.SudokuParser;
import hwr.oop.riddler.io.SudokuPrinter;
import hwr.oop.riddler.logic.solver.BacktrackingSolver;
import hwr.oop.riddler.model.Sudoku;

import java.io.File;

public class Riddler {
    public static void main(String[] args) throws IllegalArgumentException {
        var argumentParser = new RiddlerArgumentParser(args);

        var parser = new SudokuParser();
        var solver = new BacktrackingSolver();

        var sudoku = parser.parse(new File(argumentParser.sudokuFilePath()));

        Sudoku solvedSudoku = solver.solve(sudoku);

        var sudokuPrinter = new SudokuPrinter(System.out);
        sudokuPrinter.print(solvedSudoku);
    }
}
