package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.entities.impl.Converter;

import java.util.stream.Stream;

public enum ContainerType {
    Graph(),
    List(),
    Vector(List),
    Set(List, Vector);

    private java.util.List<ContainerType> acceptable;

    ContainerType(ContainerType... acceptable) {
        this.acceptable = Stream.of(acceptable).toList();
    }

    public boolean isAcceptable(ContainerType other) {
        if(other == this)
            return true;
        return acceptable.stream()
                .anyMatch(t -> t == other);
    }

    public Birthmark convert(Birthmark birthmark) {
        return switch(this) {
            case List -> Converter.toList(birthmark);
            case Set -> Converter.toSet(birthmark);
            case Vector -> Converter.toFrequency(birthmark);
            case Graph -> birthmark;
        };
    }
}
