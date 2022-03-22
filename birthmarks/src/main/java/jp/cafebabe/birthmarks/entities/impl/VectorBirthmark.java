package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.entities.elements.PairElement;
import jp.cafebabe.birthmarks.utils.Vectorable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VectorBirthmark extends AbstractBirthmark implements Serializable, Vectorable<Element, Long> {
    private static final long serialVersionUID = -7780658711069582638L;

    private Map<Element, Long> frequencies;

    VectorBirthmark(Metadata metadata, Stream<Element> stream) {
        super(metadata);
        frequencies = new HashMap<>();
        stream.forEach(p -> frequency(p));
    }

    private void frequency(Element e) {
        if(e instanceof PairElement pe) {
            this.frequencies.put(pe.element(), pe.count());
        } else {
            this.frequencies.put(e, this.frequencies.getOrDefault(e, 0L) + 1);
        }
    }

    @Override
    public Stream<Element> stream() {
        return frequencies.entrySet().stream().map(entry -> new PairElement(entry));
    }

    @Override
    public ContainerType containerType() {
        return ContainerType.Vector;
    }

    @Override
    public long size() {
        return frequencies.size();
    }

    @Override
    public Long getOrDefault(Element key, Long defaultValue) {
        return frequencies.getOrDefault(key, defaultValue);
    }

    @Override
    public Stream<Pair<Element, Long>> vectorStream() {
        return frequencies.entrySet().stream()
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()));
    }

    @Override
    public Stream<Element> keyStream() {
        return frequencies.keySet().stream();
    }

    @Override
    public Stream<Long> valueStream() {
        return frequencies.values().stream();
    }
}
