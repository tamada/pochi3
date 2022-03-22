package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractComparator implements Comparator {
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
        return calculate(left, right);
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right) throws ComparisonException;
}
