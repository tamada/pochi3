package jp.cafebabe.birthmarks.entities.elements;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serial;
import java.io.Serializable;
import java.io.Writer;

public record DoubleElement(double doubleValue) implements Element, Comparable<DoubleElement>, Serializable {
    @Serial
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
    public void marshal(Writer out) {
        Try.run(() -> out.write(String.valueOf(doubleValue())))
                .getOrElseThrow(() -> new InternalError("some error on marshaling"));
    }

    @Override
    public int compareTo(DoubleElement o) {
        return Double.compare(doubleValue, o.doubleValue);
    }
}
