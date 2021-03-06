package jp.cafebabe.birthmarks.entities;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.clpond.entities.Name;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The resultant object for the reading phase.
 *
 * @see jp.cafebabe.birthmarks.io.BirthmarksJsonier
 */
public class Birthmarks extends Results<Throwable, Birthmark> implements Serializable, Mergeable<Birthmarks>, Streamable<Birthmark> {
    @Serial
    private static final long serialVersionUID = 4476836761615526741L;

    public Birthmarks() {
        this(Stream.empty());
    }

    public Birthmarks(Stream<Either<Throwable, Birthmark>> stream) {
        super(stream);
    }

    public Optional<Birthmark> find(Name name) {
        return stream().filter(b -> b.name().equals(name))
                .findFirst();
    }

    public void accept(BirthmarkVisitor visitor) {
        stream().forEach(birthmark -> birthmark.accept(visitor));
    }

    @Override
    public Birthmarks merge(Birthmarks other) {
        return new Birthmarks(mergedStream(other));
    }
}
