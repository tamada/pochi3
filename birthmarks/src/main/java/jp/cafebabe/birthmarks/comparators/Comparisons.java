package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Results;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Comparisons extends Results<Throwable, Comparison> implements Serializable {
    private static final long serialVersionUID = 123856798190622633L;

    public Comparisons(Stream<Either<Throwable, Comparison>> stream) {
        super(stream);
    }

    public void accept(ComparisonVisitor visitor) {
        stream().forEach(item -> item.accept(visitor));
    }
}
