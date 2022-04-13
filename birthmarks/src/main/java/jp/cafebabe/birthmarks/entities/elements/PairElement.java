package jp.cafebabe.birthmarks.entities.elements;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serial;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

public record PairElement(Element element, long count) implements Element, Serializable {
    @Serial
    private static final long serialVersionUID = 488410817210454315L;

    public PairElement(Map.Entry<Element, Long> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public <E> PairElement(E value, long count) {
        this(ElementBuilder.build(value), count);
    }

    @Override
    public long intValue() {
        return count();
    }

    @Override
    public String value() {
        return element().value();
    }

    @Override
    public double doubleValue() {
        return count();
    }

    @Override
    public void marshal(Writer out) {
        Try.run(() -> out.write(String.format("\"%s=%d\"", value(), intValue())))
                .getOrElseThrow(() -> new InternalError("some error on marshaling"));
    }
}
