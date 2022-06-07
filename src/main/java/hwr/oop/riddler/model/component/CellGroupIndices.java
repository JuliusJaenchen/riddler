package hwr.oop.riddler.model.component;

public record CellGroupIndices(int row, int column, int box) {
    public int getCellGroupIndex(CellGroupType type) {
        return switch (type) {
            case ROW -> row();
            case COLUMN -> column();
            case BOX -> box();
        };
    }
}
