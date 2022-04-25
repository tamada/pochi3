package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.pairers.Pairer;

import java.util.Arrays;
import java.util.stream.Stream;

public interface Comparator {
    Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair);

    default ContainerType acceptableType() {
        return type().acceptable();
    }

    ComparatorType type();

    default boolean isAcceptable(ContainerType type) {
        return Stream.of(type().acceptable())
                .anyMatch(t -> t.isAcceptable(type));
    }

    default Comparisons compare(Birthmarks birthmarks, Pairer<Birthmark> pairer) {
        return new Comparisons(pairer.pair(birthmarks)
                .map(this::compareBirthmark));
    }

    default Comparisons compare(Birthmarks left, Birthmarks right, Pairer<Birthmark> pairer) {
        return new Comparisons(pairer.pair(left, right)
                .map(this::compareBirthmark));
    }

    default Either<Throwable, Comparison> compareBirthmark(Pair<Birthmark, Birthmark> pair) {
        return similarity(pair)
                .map(similarity -> new Comparison(pair, similarity));
    }
}
