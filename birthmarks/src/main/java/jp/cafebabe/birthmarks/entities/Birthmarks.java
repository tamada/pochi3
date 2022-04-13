package jp.cafebabe.birthmarks.entities;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.io.BirthmarksMarshaller;
import jp.cafebabe.birthmarks.utils.Jsonable;
import jp.cafebabe.clpond.entities.Name;

import java.io.Serial;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Stream;

public class Birthmarks extends Results<Throwable, Birthmark> implements Serializable, Jsonable, Mergeable<Birthmarks> {
    @Serial
    private static final long serialVersionUID = 4476836761615526741L;

    public Birthmarks(Stream<Either<Throwable, Birthmark>> stream) {
        super(stream);
    }

    public Optional<Birthmark> find(Name name) {
        return stream().filter(b -> b.name().equals(name))
                .findFirst();
    }

    @Override
    public String toJson() {
        StringWriter out = new StringWriter();
        new BirthmarksMarshaller(out).marshal(this);
        return out.toString();
    }

    public void accept(BirthmarkVisitor visitor) {
        stream().forEach(birthmark -> birthmark.accept(visitor));
    }

    @Override
    public Birthmarks merge(Birthmarks other) {
        return new Birthmarks(mergedStream(other));
    }
}
