package jp.cafebabe.birthmarks.entities.elements;

import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serial;
import java.io.Serializable;

public record LongElement(long intValue) implements Element, Comparable<LongElement>, Serializable {
    @Serial
    private static final long serialVersionUID = 1561122888626996378L;

    @Override
    public double doubleValue() {
        return intValue;
    }

    @Override
    public String value() {
        return String.valueOf(intValue);
    }

    @Override
    public void marshal(Marshaller out) {
        out.marshal(String.valueOf(intValue()));
    }

    @Override
    public int compareTo(LongElement o) {
        return Long.compare(intValue, o.intValue);
    }
}
