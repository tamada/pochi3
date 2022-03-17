package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.extractors.Birthmark;
import jp.cafebabe.birthmarks.extractors.Birthmarks;
import jp.cafebabe.birthmarks.pairers.Pairer;

public interface Comparator {
    Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair);

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
