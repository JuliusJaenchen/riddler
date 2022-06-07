package hwr.oop.riddler.model.component;

import lombok.Getter;

public class Cell {
    @Getter
    private final CellGroupIndices cellGroupIndices;

    //went with the following solution instead of one cellContent attribute to avoid casts and instanceof checks
    private UnfilledCellContent unfilledContent;
    private FilledCellContent filledContent;

    public Cell(CellGroupIndices cellGroupIndices) {
        this.cellGroupIndices = cellGroupIndices;
        unfilledContent = new UnfilledCellContent();
    }

    public Cell(int value, CellGroupIndices cellGroupIndices) {
        this.filledContent = new FilledCellContent(value);
        this.cellGroupIndices = cellGroupIndices;
    }

    public Cell(Cell cell) {
        this.unfilledContent = new UnfilledCellContent(cell.unfilledContent);
        this.filledContent = cell.filledContent;
        this.cellGroupIndices = cell.cellGroupIndices;
    }

    public boolean isFilled() {
        return filledContent != null;
    }

    public boolean isEmpty() {
        return !isFilled();
    }

    public int getValue() {
        if (isEmpty()) {
            throw new IllegalStateException("empty cell has no numericValue");
        }
        return filledContent.value();
    }

    public void setValue(int value) {
        if (value < 1)
            throw new IllegalArgumentException("Value must be greater than 0");
        if (this.isFilled())
            throw new IllegalStateException("Value was already set");
        this.unfilledContent = null;
        this.filledContent = new FilledCellContent(value);
    }

    public boolean addImpossible(int impossibleValue) {
        if (impossibleValue < 1)
            throw new IllegalArgumentException("Possible must be greater than 0");
        throw new UnsupportedOperationException("removed function");
    }

    public UnfilledCellContent getUnfilledContent() {
        if (isFilled())
            throw new IllegalStateException("Cannot get unfilled cellcontent of filled cell");
        return unfilledContent;
    }

    public FilledCellContent getFilledContent() {
        if (isEmpty())
            throw new IllegalStateException("Cannot get filled cellcontent of empty cell");
        return filledContent;
    }
}
