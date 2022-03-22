package jp.cafebabe.birthmarks.utils;

import jp.cafebabe.birthmarks.comparators.Pair;

import java.util.stream.Stream;

public interface Vectorable<K, V extends Number> {
    Stream<K> keyStream();

    Stream<V> valueStream();

    Stream<Pair<K, V>> vectorStream();

    V getOrDefault(K key, V defaultValue);
}
