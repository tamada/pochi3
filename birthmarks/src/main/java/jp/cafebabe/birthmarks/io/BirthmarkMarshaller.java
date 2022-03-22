package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.*;
import jp.cafebabe.clpond.entities.Name;

import java.io.Writer;
import java.net.URI;

public class BirthmarkMarshaller implements BirthmarkVisitor {
    private final Marshaller marshaller;

    private BirthmarkMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public static BirthmarkMarshaller of(Writer out) {
        return of(Marshaller.of(out));
    }

    public static BirthmarkMarshaller of(Marshaller marshaller) {
        return new BirthmarkMarshaller(marshaller);
    }

    public void marshal(Birthmarks birthmarks) {
        marshaller.marshal("[");
        birthmarks.stream().forEach(this::marshal);
        marshaller.marshal("]");
    }

    public void marshal(Birthmark birthmark) {
        birthmark.accept(this);
    }

    @Override
    public void visitMetadata(Name name, URI location, BirthmarkType type) {
        marshaller.marshal(String.format(
                ",\"metadata\":{\"name\":\"%s\",\"location\":\"%s\",\"type\":\"%s\"}",
                name.name(), location, type.type()));
    }

    @Override
    public void visit(Birthmark birthmark) {
        marshaller.marshal("{");
        marshaller.marshalKeyAndValue("container", birthmark.containerType().name().toLowerCase());
    }

    @Override
    public void visitElementCount(long max) {
        marshaller.marshal(",\"elements\":[");
    }

    @Override
    public void visitElement(Element element, boolean isLast) {
        element.marshal(marshaller);
        if(!isLast)
            marshaller.marshal(",");
    }

    @Override
    public void visitEnd() {
        marshaller.marshal("]}");
    }
}
