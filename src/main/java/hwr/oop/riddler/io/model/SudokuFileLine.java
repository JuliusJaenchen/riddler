package hwr.oop.riddler.io.model;

import java.util.HashSet;
import java.util.Set;

public record SudokuFileLine(Set<LineValue> values) {
    public static SudokuFileLine fromString(String sanitizedLine) {
        Set<LineValue> lineValues = new HashSet<>();
        for (int lineIndex = 0; lineIndex < sanitizedLine.length(); lineIndex++) {
            var numericValue = Character.getNumericValue(sanitizedLine.charAt(lineIndex));
            var lineValue = new LineValue(lineIndex, numericValue);
            lineValues.add(lineValue);
        }
        return new SudokuFileLine(lineValues);
    }
}
