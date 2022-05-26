package jp.cafebabe.birthmarks.utils;

import java.util.stream.Stream;

/**
 * The implemented classes of this interface returns the stream of E.
 * @param <E>
 */
@FunctionalInterface
public interface Streamable<E> {
    Stream<E> stream();

    /**
     * This cost of this method is high, since the default implementation of <code>size</code> method is {@code stream().count()}.
     * Therefore, the implemented classes should redefine the method in cheaper cost.
     *
     * @return the stream count of the {@link #stream()} method.
     */
    default long size() {
        return stream().count();
    }
}
