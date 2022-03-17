package jp.cafebabe.birthmarks.extractors.impl;

import jp.cafebabe.birthmarks.extractors.elements.ElementBuilder;
import jp.cafebabe.birthmarks.extractors.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Builder {
    private Name name;
    private URI location;
    private BirthmarkType type;

    public Builder name(Name name) {
        this.name = name;
        return this;
    }

    public Builder metadata(String name, URI location, String type) {
        return name(name)
                .location(location)
                .type(type);
    }

    public Builder name(String name) {
        return name(Name.of(name));
    }

    public Builder type(String type) {
        return type(new BirthmarkType(type));
    }

    public Builder type(BirthmarkType type) {
        this.type = type;
        return this;
    }

    public Builder location(URI location) {
        this.location = location;
        return this;
    }

    private void validate() {
        List<String> errors = new ArrayList<>();
        if(name == null) errors.add("name");
        if(type == null) errors.add("type");
        if(location == null) errors.add("location");
        if(errors.size() != 0)
            throw new IllegalStateException(String.format("%s: must not null",
                    errors.stream().collect(Collectors.joining(", "))));
    }

    public <E> Birthmark build(Stream<E> stream, ContainerType type) {
        return switch(type) {
            case List -> list(stream);
            case Set -> set(stream);
            case Vector -> vector(stream);
            case Graph -> null;
            default -> null;
        };
    }

    public <E> Birthmark list(Stream<E> stream) {
        return list(stream, ElementBuilder::build);
    }

    public <E> Birthmark list(Stream<E> stream, Function<E, Element> mapper) {
        validate();
        return new ListBirthmark(new Metadata(name, location, type),
                stream.map(mapper));
    }

    public <E> Birthmark set(Stream<E> stream) {
        return set(stream, ElementBuilder::build);
    }

    public <E> Birthmark set(Stream<E> stream, Function<E, Element> mapper) {
        validate();
        return new SetBirthmark(new Metadata(name, location, type),
                stream.map(mapper));
    }

    public <E> Birthmark vector(Stream<E> stream) {
        return vector(stream, ElementBuilder::build);
    }

    public <E> Birthmark vector(Stream<E> stream, Function<E, Element> mapper) {
        validate();
        return new VectorBirthmark(new Metadata(name, location, type),
                stream.map(mapper));
    }
}
