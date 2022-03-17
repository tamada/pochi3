package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serializable;
import java.util.Objects;

public record ObjectElement<E>(E rawValue, Stringer<E> stringer) implements Element, Serializable {
    private static final long serialVersionUID = -3563473007533917216L;

    public ObjectElement(E value) {
        this(value, v -> v.toString());
    }

    public ObjectElement(E rawValue, Stringer<E> stringer) {
        this.rawValue = Objects.requireNonNull(rawValue);
        this.stringer = Objects.requireNonNull(stringer);
    }

    @Override
    public long intValue() {
        throw new IllegalStateException("ObjectElement have no intValue");
    }

    @Override
    public String value() {
        return stringer.toString(rawValue);
    }

    @Override
    public double doubleValue() {
        throw new IllegalStateException("ObjectElement have no intValue");
    }

    @Override
    public void marshal(Marshaller out) {
        out.marshal(String.format("\"%s\"", value()));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ObjectElement oe)
                && Objects.equals(rawValue, oe.rawValue);
    }
}
