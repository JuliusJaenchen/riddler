package hwr.oop.riddler.model.component;

public record CellGroupIndicators(int row, int column, int box) {
    public int getCellGroupIndicator(CellGroupType type) {
        return switch (type) {
            case ROW -> row();
            case COLUMN -> column();
            case BOX -> box();
        };
    }
}
