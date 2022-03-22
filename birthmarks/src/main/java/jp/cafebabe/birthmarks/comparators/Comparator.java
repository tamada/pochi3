package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.pairers.Pairer;

import java.util.Arrays;

public interface Comparator {
    Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair);

    default boolean isAcceptable(ContainerType type) {
        return Arrays.stream(acceptableTypes())
                .map(acceptableType -> type == acceptableType)
                .reduce(false, (b1, b2) -> b1 || b2);
    }

    ContainerType[] acceptableTypes();

    default Comparisons compare(Birthmarks birthmarks, Pairer pairer) {
        return new Comparisons(pairer.pair(birthmarks)
                .map(pair -> compareBirthmark(pair)));
    }

    default Comparisons compare(Birthmarks left, Birthmarks right, Pairer pairer) {
        return new Comparisons(pairer.pair(left, right)
                .map(pair -> compareBirthmark(pair)));
    }

    default Either<Throwable, Comparison> compareBirthmark(Pair<Birthmark, Birthmark> pair) {
        return similarity(pair)
                .map(similarity -> new Comparison(pair, similarity));
    }
}
