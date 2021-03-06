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
    private ComparatorType type;

    public AbstractComparator(Configuration config, ComparatorType type) {
        this.config = config;
        this.type = type;
    }

    protected Configuration config() {
        return config;
    }

    public final ComparatorType type() {
        return type;
    }

    public Either<Throwable, Similarity> similarity(Pair<Birthmark, Birthmark> pair) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.BeforeEach, pair));
        var result = Try.of(() -> calculateSimilarity(pair.left(), pair.right()))
                .toEither();
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.AfterEach, result));
        return result;
    }

    private Similarity calculateSimilarity(Birthmark left, Birthmark right) throws ComparisonException {
        if(!isAcceptable(left.containerType()))
            throw new ComparisonException(left.name().name() + ": not match of " + getClass().getName(), left, right);
        if(!isAcceptable(right.containerType()))
            throw new ComparisonException(right.name().name() + ": not match of " + getClass().getName(), left, right);
        if(left.containerType() != right.containerType())
            throw new ComparisonException(String.format("container types do not match (%s, %s)", left.containerType(), right.containerType()), left, right);
        if(!Objects.equals(left.type(), right.type()))
            throw new ComparisonException(String.format("birthmark types do not match (%s, %s)", left.type(), right.type()), left, right);
        return calculateSimilarityImpl(left, right);
    }

    private Similarity calculateSimilarityImpl(Birthmark left, Birthmark right) throws ComparisonException {
        if(left.size() == 0 && right.size() == 0)
            return new Similarity(1d);
        else if(left.size() == 0 || right.size() == 0)
            return new Similarity(0d);
        return calculate(left, right);
    }

    public Comparisons compare(Birthmarks birthmarks, Pairer<Birthmark> pairer) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.Before, birthmarks));
        var result = Comparator.super.compare(birthmarks, pairer);
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.After, result));
        return result;
    }

    public Comparisons compare(Birthmarks left, Birthmarks right, Pairer<Birthmark> pairer) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.Before, Pair.of(left, right)));
        var result = Comparator.super.compare(left, right, pairer);
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.After, result));
        return result;
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right) throws ComparisonException;
}
