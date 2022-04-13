package jp.cafebabe.birthmarks.entities;

public interface Mergeable<T> {
    T merge(T other);
}
