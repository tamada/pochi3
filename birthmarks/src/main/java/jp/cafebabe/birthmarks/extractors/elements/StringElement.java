package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serializable;
import java.util.Objects;

public record StringElement(String value) implements Element, Comparable<StringElement>, Serializable {
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
    public void marshal(Marshaller out) {
        out.marshal(String.format("\"%s\"", value()));
    }

    @Override
    public int compareTo(StringElement o) {
        return value.compareTo(o.value);
    }
}
