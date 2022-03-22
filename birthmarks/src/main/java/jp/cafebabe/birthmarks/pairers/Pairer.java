package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;

import java.util.stream.Stream;

public interface Pairer<T extends Namer> {
    Stream<Pair<T, T>> pair(Streamable<T> targets);

    Stream<Pair<T, T>> pair(Streamable<T> target1, Streamable<T> target2);

    long count(Streamable<T> target);

    long count(Streamable<T> target1, Streamable<T> target2);
}
