package jp.cafebabe.birthmarks.entities;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.utils.Streamable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Results<L extends Throwable, R> implements Serializable, Streamable<R> {
    @Serial
    private static final long serialVersionUID = -1608662815109768884L;

    private final List<Either<L, R>> list;

    public Results(Stream<Either<L, R>> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public long size() {
        return list.size();
    }

    protected Stream<Either<L, R>> filterImpl(Predicate<R> predicate) {
        return list.stream()
                .filter(either -> either.isLeft() || either.filter(predicate).isDefined());
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
                .anyMatch(Either::isLeft);
    }

    protected Stream<Either<L, R>> mergedStream(Results<L, R> other) {
        if(other != null)
            return Stream.concat(list.stream(), other.list.stream());
        return list.stream();
    }
}
