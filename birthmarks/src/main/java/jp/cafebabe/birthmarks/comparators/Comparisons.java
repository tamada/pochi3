package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Mergeable;
import jp.cafebabe.birthmarks.entities.Results;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * The resultant objects of the comparing phase.
 *
 * @see jp.cafebabe.birthmarks.io.ComparisonsJsonier
 */
public class Comparisons extends Results<Throwable, Comparison> implements Serializable, Mergeable<Comparisons> {
    @Serial
    private static final long serialVersionUID = 123856798190622633L;

    public Comparisons(Stream<Either<Throwable, Comparison>> stream) {
        super(stream);
    }

    public void accept(ComparisonVisitor visitor) {
        stream().forEach(item -> item.accept(visitor));
    }

    @Override
    public Comparisons merge(Comparisons other) {
        return new Comparisons(mergedStream(other));
    }

    public Comparisons filter(Predicate<Comparison> predicate) {
        return new Comparisons(filterImpl(predicate));
    }
}
