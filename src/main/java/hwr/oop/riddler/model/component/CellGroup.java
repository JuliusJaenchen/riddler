package hwr.oop.riddler.model.component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public record CellGroup(Set<Cell> cells) {
    public Set<Integer> getCellValues() {
        return cells.stream()
                .filter(Cell::isFilled)
                .map(Cell::getValue)
                .collect(toSet());
    }
}
