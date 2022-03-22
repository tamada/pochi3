package jp.cafebabe.birthmarks.entities.elements;

@FunctionalInterface
public interface Stringer<E> {
    String toString(E target);
}
