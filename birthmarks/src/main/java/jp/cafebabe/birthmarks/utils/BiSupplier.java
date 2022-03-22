package jp.cafebabe.birthmarks.utils;

@FunctionalInterface
public interface BiSupplier<A1, A2, R> {
    R get(A1 a1, A2 a2);
}
