package jp.cafebabe.birthmarks.extractors.impl;

import jp.cafebabe.birthmarks.extractors.ContainerType;
import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.extractors.Metadata;
import jp.cafebabe.birthmarks.extractors.elements.PairElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VectorBirthmark extends AbstractBirthmark implements Serializable {
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
    public int elementCount() {
        return frequencies.size();
    }
}
