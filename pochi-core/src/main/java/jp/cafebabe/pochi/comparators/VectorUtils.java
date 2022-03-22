package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.utils.Vectorable;

import java.util.stream.Stream;

public class VectorUtils {
    public static <K, V extends Number> double norm(Vectorable<K, V> v) {
        return Math.sqrt(v.valueStream()
                .mapToDouble(n -> n.doubleValue() * n.doubleValue())
                .reduce(0, (b, c) -> b + c));

    }

    public static <K> double innerProduct(Vectorable<K, Long> left, Vectorable<K, Long> right) {
        Stream<K> keyStream = keyStream(left, right);
        return keyStream.map(key -> product(key, left, right))
                .reduce(0L, (b, c) -> b + c);
    }

    private static <K> long product(K key, Vectorable<K, Long> left, Vectorable<K, Long> right) {
        return left.getOrDefault(key, 0L) *
                right.getOrDefault(key, 0L);
    }

    private static <K> Stream<K> keyStream(Vectorable<K, ?> left, Vectorable<K, ?> right) {
        return Stream.concat(left.keyStream(), right.keyStream())
                .distinct();
    }

}
