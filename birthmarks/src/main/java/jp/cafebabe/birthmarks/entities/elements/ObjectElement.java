package jp.cafebabe.birthmarks.entities.elements;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serial;
import java.io.Serializable;
import java.io.Writer;
import java.util.Objects;
import java.util.function.Function;

public record ObjectElement<E>(E rawValue, Function<E, String> stringer) implements Element, Serializable {
    @Serial
    private static final long serialVersionUID = -3563473007533917216L;

    public ObjectElement(E value) {
        this(value, Object::toString);
    }

    public ObjectElement(E rawValue, Function<E, String> stringer) {
        this.rawValue = Objects.requireNonNull(rawValue);
        this.stringer = Objects.requireNonNull(stringer);
    }

    @Override
    public long intValue() {
        throw new IllegalStateException("ObjectElement have no intValue");
    }

    @Override
    public String value() {
        return stringer.apply(rawValue);
    }

    @Override
    public double doubleValue() {
        throw new IllegalStateException("ObjectElement have no intValue");
    }

    @Override
    public void marshal(Writer out) {
        Try.run(() -> out.write(String.format("\"%s\"", value())))
                .getOrElseThrow(() -> new InternalError("some error on marshaling"));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ObjectElement oe)
                && Objects.equals(rawValue, oe.rawValue);
    }
}
