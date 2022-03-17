package jp.cafebabe.birthmarks.extractors;

import java.net.URI;

public interface BirthmarkVisitor {
    void visitMetadata(Name name, URI location, BirthmarkType type);

    void visit(Birthmark birthmark);

    void visitElementCount(int max);

    void visitElement(Element element, boolean isLast);

    void visitEnd();
}
