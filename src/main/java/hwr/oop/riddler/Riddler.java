package hwr.oop.riddler;

import hwr.oop.riddler.io.RiddlerArguments;
import hwr.oop.riddler.io.parser.SudokuParser;
import hwr.oop.riddler.io.SudokuPrinter;
import hwr.oop.riddler.logic.solver.BacktrackingSolver;
import hwr.oop.riddler.model.Sudoku;

public class Riddler {
    public static void main(String[] args) throws IllegalArgumentException {
        var arguments = new RiddlerArguments(args);

        var sudokuParser = new SudokuParser();
        var solver = new BacktrackingSolver();

        var sudoku = sudokuParser.parse(arguments.sudokuFilePath());

        Sudoku solvedSudoku = solver.getSolvedSudoku(sudoku);

        var sudokuPrinter = new SudokuPrinter(System.out);
        sudokuPrinter.print(solvedSudoku);
    }
}
