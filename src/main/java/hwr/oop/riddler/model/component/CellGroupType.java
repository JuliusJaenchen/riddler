package hwr.oop.riddler.model.component;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum CellGroupType {
    ROW(CellPosition::row), BOX(CellPosition::box), COLUMN(CellPosition::column);

    final Function<CellPosition, Integer> indexFunction;

    CellGroupType(Function<CellPosition, Integer> indexFunction) {
        this.indexFunction = indexFunction;
    }

    private Function<CellPosition, Integer> getIndexFunction() {
        return indexFunction;
    }

    public int typeIndexOfPosition(CellPosition position) {
        return getIndexFunction().apply(position);
    }
}
