package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.clpond.entities.Name;

import java.io.Serializable;
import java.net.URI;
import java.util.stream.Stream;

public interface Birthmark extends Serializable, Streamable<Element> {
    Metadata metadata();

    Stream<Element> stream();

    ContainerType containerType();

    int elementCount();

    default BirthmarkType type() {
        return metadata().type();
    }

    default URI location() {
        return metadata().location();
    }

    default Name name() {
        return metadata().name();
    }

    default void accept(BirthmarkVisitor visitor) {
        visitor.visit(this);
        metadata().accept(visitor);
        visitor.visitElementCount(elementCount());
        var index = Cursor.of(elementCount());
        stream().forEach(element -> visitor.visitElement(element, index.incrementIsLast()));
        visitor.visitEnd();
    }
}
