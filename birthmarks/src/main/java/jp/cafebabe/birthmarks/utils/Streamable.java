package jp.cafebabe.birthmarks.utils;

import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<E> {
    Stream<E> stream();

    default long size() {
        return stream().count();
    }
}
