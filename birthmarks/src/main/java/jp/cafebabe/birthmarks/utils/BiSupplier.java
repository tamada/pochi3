package jp.cafebabe.birthmarks.utils;

/**
 * The supplier interface receiving two parameters.
 *
 * @param <A1> the first parameter.
 * @param <A2> the second parameter.
 * @param <R> resultant object.
 */
@FunctionalInterface
public interface BiSupplier<A1, A2, R> {
    R get(A1 a1, A2 a2);
}
