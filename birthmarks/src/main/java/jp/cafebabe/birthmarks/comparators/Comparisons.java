package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Comparisons implements Serializable {
    private static final long serialVersionUID = 123856798190622633L;

    private List<Either<Throwable, Comparison>> list;

    public Comparisons(Stream<Either<Throwable, Comparison>> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public boolean hasExceptions() {
        return list.stream().filter(Either::isLeft).count() > 0;
    }

    public Stream<Throwable> exceptions() {
        return streamImpl(Either::isLeft, Either::getLeft);
    }

    public Stream<Comparison> stream() {
        return streamImpl(Either::isRight, Either::get);
    }

    private <E> Stream<E> streamImpl(Predicate<Either<Throwable, Comparison>> p, Function<Either<Throwable, Comparison>, E> mapper) {
        return list.stream().filter(p)
                .map(mapper);
    }

    public void accept(ComparisonVisitor visitor) {
        stream().forEach(c -> c.accept(visitor));
    }
}
