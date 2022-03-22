package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;

import java.util.stream.Stream;

public interface Pairer {
    Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks birthmarks);

    Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks left, Birthmarks right);

    long count(Birthmarks birthmarks);

    long count(Birthmarks left, Birthmarks right);
}
