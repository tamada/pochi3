package jp.cafebabe.birthmarks.io;

public interface Jsonier<T> {
    String toJson(T target);
}
