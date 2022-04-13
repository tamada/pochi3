package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.BirthmarkVisitor;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.clpond.entities.Name;

import java.io.Writer;
import java.net.URI;

public class BirthmarkMarshaller implements BirthmarkVisitor {
    private Marshaller marshaller;

    public BirthmarkMarshaller(Writer out) {
        this(Marshaller.of(out));
    }

    public BirthmarkMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void marshal(Birthmark b) {
        b.accept(this);
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
