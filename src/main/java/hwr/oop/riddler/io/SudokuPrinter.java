package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SudokuPrinter {
    private final OutputStream outputStream;

    public SudokuPrinter(OutputStream out) {
        this.outputStream = out;
    }

    public void print(Sudoku sudoku) {
        try (var outputStreamWriter = new OutputStreamWriter(outputStream);
             var bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            bufferedWriter.write(sudokuToString(sudoku));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sudokuToString(Sudoku sudoku) {
        var builder = new StringBuilder();

        builder.append("------ Solved Sudoku -------\n");
        for (int row = 0; row < sudoku.getSize(); row++) {
            for (int column = 0; column < sudoku.getSize(); column++) {
                var cellOptional = sudoku.getCellAt(row, column);
                builder.append(cellOptional.map(this::cellToString).orElse("?"));
                builder.append(" ");
            }
            builder.append("\n");
        }

        builder.append("----------------------------\n");
        return builder.toString();
    }

    private String cellToString(Cell cell) {
        return cell.isFilled() ? String.valueOf(cell.getFilledContent().value()) : "_";
    }
}
