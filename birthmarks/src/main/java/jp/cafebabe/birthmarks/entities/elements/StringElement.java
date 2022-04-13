package jp.cafebabe.birthmarks.entities.elements;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serial;
import java.io.Serializable;
import java.io.Writer;
import java.util.Objects;

public record StringElement(String value) implements Element, Comparable<StringElement>, Serializable {
    @Serial
    private static final long serialVersionUID = 5050804023307026236L;

    public StringElement(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public long intValue() {
        throw new IllegalStateException("DefaultElement have no intValue");
    }

    @Override
    public double doubleValue() {
        throw new IllegalStateException("DefaultElement have no doubleValue");
    }

    @Override
    public void marshal(Writer out) {
        Try.run(() -> out.write(String.format("\"%s\"", value())))
                .getOrElseThrow(() -> new InternalError("some error on marshaling"));
    }

    @Override
    public int compareTo(StringElement o) {
        return value.compareTo(o.value);
    }
}
