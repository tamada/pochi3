package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Metadata;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetBirthmark extends AbstractBirthmark implements Serializable {
    private static final long serialVersionUID = 7541332102190645261L;

    private Set<Element> set;

    SetBirthmark(Metadata metadata, Stream<Element> stream) {
        super(metadata);
        this.set = stream.collect(Collectors.toSet());
    }

    @Override
    public Stream<Element> stream() {
        return set.stream();
    }

    @Override
    public ContainerType containerType() {
        return ContainerType.Set;
    }

    @Override
    public int elementCount() {
        return set.size();
    }
}
