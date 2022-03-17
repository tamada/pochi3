package jp.cafebabe.birthmarks.extractors.impl;

import jp.cafebabe.birthmarks.extractors.ContainerType;
import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.extractors.Metadata;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListBirthmark extends AbstractBirthmark implements Serializable {
    private static final long serialVersionUID = 2416437418655670500L;

    private List<Element> elements;

    ListBirthmark(Metadata metadata, Stream<Element> stream) {
        super(metadata);
        this.elements = stream.collect(Collectors.toList());
    }

    @Override
    public Stream<Element> stream() {
        return elements.stream();
    }

    @Override
    public ContainerType containerType() {
        return ContainerType.List;
    }

    @Override
    public int elementCount() {
        return elements.size();
    }
}
