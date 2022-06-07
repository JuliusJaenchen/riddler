package hwr.oop.riddler.model.component;

import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UnfilledCellContent implements CellContent {
    @Getter
    private final Set<Integer> impossibles;

    public UnfilledCellContent() {
        this.impossibles = new HashSet<>();
    }

    public UnfilledCellContent(UnfilledCellContent unfilledContent) {
        this.impossibles = new HashSet<>(unfilledContent.impossibles);
    }

    public void addImpossible(int impossible) {
        impossibles.add(impossible);
    }

    public boolean addImpossibles(Collection<Integer> impossibles) {
        return this.impossibles.addAll(impossibles);
    }
}
