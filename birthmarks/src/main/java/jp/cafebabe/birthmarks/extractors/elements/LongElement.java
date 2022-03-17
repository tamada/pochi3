package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serializable;

public record LongElement(long intValue) implements Element, Comparable<LongElement>, Serializable {
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
