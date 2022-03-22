package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.clpond.entities.Name;

import java.net.URI;

public interface BirthmarkVisitor {
    void visitMetadata(Name name, URI location, BirthmarkType type);

    void visit(Birthmark birthmark);

    void visitElementCount(long max);

    void visitElement(Element element, boolean isLast);

    void visitEnd();
}
