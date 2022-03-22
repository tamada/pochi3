package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.pairers.Pairer;

import java.util.Arrays;

public interface Comparator {
    Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair);

    ContainerType[] acceptableTypes();

    default boolean isAcceptable(ContainerType type) {
        return Arrays.stream(acceptableTypes())
                .anyMatch(acceptableType -> type == acceptableType);
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
