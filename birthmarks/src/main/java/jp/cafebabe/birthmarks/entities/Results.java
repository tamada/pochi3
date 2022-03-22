package jp.cafebabe.birthmarks.entities;

import io.vavr.control.Either;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Results<L extends Throwable, R> implements Serializable {
    private static final long serialVersionUID = -1608662815109768884L;

    private List<Either<L, R>> list;

    public Results(Stream<Either<L, R>> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public int size() {
        return list.size();
    }

    public Stream<R> stream() {
        return streamImpl(Either::isRight, Either::get);
    }

    public Stream<L> failures() {
        return streamImpl(Either::isLeft, Either::getLeft);
    }

    private <K> Stream<K> streamImpl(Predicate<Either<L, R>> predicate, Function<Either<L, R>, K> mapper) {
        return list.stream()
                .filter(predicate)
                .map(mapper);
    }

    public boolean hasFailure() {
        return list.stream()
                .filter(Either::isLeft)
                .count() > 0;
    }
}
