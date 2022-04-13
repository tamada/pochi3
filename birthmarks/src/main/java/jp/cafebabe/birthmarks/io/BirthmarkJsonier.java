package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.BirthmarkVisitor;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.clpond.entities.Name;

import java.io.StringWriter;
import java.net.URI;

public class BirthmarkJsonier implements BirthmarkVisitor, Jsonier<Birthmark> {
    private StringWriter out;

    public String toJson(Birthmark b) {
        out = new StringWriter();
        b.accept(this);
        return out.toString();
    }

    @Override
    public void visitMetadata(Name name, URI location, BirthmarkType type) {
        out.write(String.format(
                ",\"metadata\":{\"name\":\"%s\",\"location\":\"%s\",\"type\":\"%s\"}",
                name.name(), location, type.type()));
    }

    @Override
    public void visit(Birthmark birthmark) {
        out.write("{\"container\":\"");
        out.write(birthmark.containerType().name().toLowerCase());
        out.write("\"");
    }

    @Override
    public void visitElementCount(long max) {
        out.write(",\"elements\":[");
    }

    @Override
    public void visitElement(Element element, boolean isLast) {
        element.marshal(out);
        if(!isLast)
            out.write(",");
    }

    @Override
    public void visitEnd() {
        out.write("]}");
    }
}
