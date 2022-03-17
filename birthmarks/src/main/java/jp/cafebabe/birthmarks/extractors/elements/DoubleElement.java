package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serializable;

public record DoubleElement(double doubleValue) implements Element, Comparable<DoubleElement>, Serializable {
    private static final long serialVersionUID = 6077017653958264360L;

    @Override
    public long intValue() {
        throw new IllegalStateException("DoubleElement have no intValue");
    }

    @Override
    public String value() {
        return String.valueOf(doubleValue);
    }

    @Override
    public void marshal(Marshaller out) {
        out.marshal(String.valueOf(doubleValue()));
    }

    @Override
    public int compareTo(DoubleElement o) {
        return Double.compare(doubleValue, o.doubleValue);
    }
}
