package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.events.BirthmarkEvent;
import jp.cafebabe.birthmarks.pairers.Pairer;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractComparator extends Task implements Comparator {
    private final Configuration config;

    public AbstractComparator(Configuration config) {
        this.config = config;
    }

    protected Configuration config() {
        return config;
    }

    public Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair) {
        return Try.of(() -> calculateSimilarity(pair.left(), pair.right()))
                .toEither();
    }

    public final boolean isAcceptable(ContainerType type) {
        return Arrays.stream(acceptableTypes())
                .map(acceptableType -> type == acceptableType)
                .reduce(false, (b1, b2) -> b1 || b2);
    }

    private Similarity calculateSimilarity(Birthmark left, Birthmark right) throws ComparisonException {
        if(!isAcceptable(left.containerType()))
            throw new ComparisonException(left.name() + ": not match of " + getClass().getName(), left, right);
        if(!isAcceptable(right.containerType()))
            throw new ComparisonException(right.name() + ": not match of " + getClass().getName(), left, right);
        if(left.containerType() != right.containerType())
            throw new ComparisonException(String.format("container types do not match (%s, %s)", left.containerType(), right.containerType()), left, right);
        if(!Objects.equals(left.type(), right.type()))
            throw new ComparisonException(String.format("birthmark types do not match (%s, %s)", left.type(), right.type()), left, right);
        return calculateImpl(left, right);
    }

    public Comparisons compare(Birthmarks birthmarks, Pairer<Birthmark> pairer) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.Before, birthmarks));
        var result = new Comparisons(pairer.pair(birthmarks)
                .map(this::compareBirthmark));
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.After, birthmarks));
        return result;
    }

    public Comparisons compare(Birthmarks left, Birthmarks right, Pairer<Birthmark> pairer) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.Before, Pair.of(left, right)));
        var result = new Comparisons(pairer.pair(left, right)
                .map(this::compareBirthmark));
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.After, Pair.of(left, right)));
        return result;
    }

    private Similarity calculateImpl(Birthmark left, Birthmark right) throws ComparisonException {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.BeforeEach, Pair.of(left, right)));
        var similarity = calculate(left, right);
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.AfterEach, Pair.of(left, right)));
        return similarity;
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right) throws ComparisonException;
}
