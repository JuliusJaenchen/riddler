package hwr.oop.riddler.model.component;

public record FilledCellContent(int value) implements CellContent {
    public static FilledCellContent fromInteger(int value) {
        //todo move to cellfactory
        return new FilledCellContent(value);
    }

    @Override
    public void ifEmpty(Runnable runnable) {

    }

    @Override
    public boolean isValid() {
        return true;
    }
}
