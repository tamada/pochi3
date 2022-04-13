package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.*;
import jp.cafebabe.clpond.entities.Name;

import java.io.Writer;
import java.net.URI;
import java.util.stream.Collectors;

public class BirthmarksMarshaller {
    private final Marshaller marshaller;

    public BirthmarksMarshaller(Writer out) {
        this(Marshaller.of(out));
    }

    public BirthmarksMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void marshal(Birthmarks birthmarks) {
        marshaller.marshal("[");
        marshaller.marshal(birthmarks.stream()
                .map(Birthmark::toJson)
                .collect(Collectors.joining(",")));
        marshaller.marshal("]");
    }
}
