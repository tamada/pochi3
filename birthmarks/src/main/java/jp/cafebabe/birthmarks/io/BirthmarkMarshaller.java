package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.extractors.Birthmark;
import jp.cafebabe.birthmarks.extractors.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.extractors.Name;
import jp.cafebabe.birthmarks.extractors.BirthmarkVisitor;

import java.net.URI;

class BirthmarkMarshaller implements BirthmarkVisitor {
    private Marshaller marshaller;

    BirthmarkMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
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
        marshaller.marshal("container", birthmark.containerType().name().toLowerCase());
    }

    @Override
    public void visitElementCount(int max) {
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
