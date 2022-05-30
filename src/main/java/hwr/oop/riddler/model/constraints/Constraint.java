package hwr.oop.riddler.model.constraints;

public interface Constraint<T> {
    boolean isSatisfiedBy(T object);
}
