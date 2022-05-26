package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.clpond.entities.Name;

import java.io.Serializable;
import java.net.URI;
import java.util.stream.Stream;

/**
 * @see BirthmarkVisitor
 * @see jp.cafebabe.birthmarks.io.BirthmarkJsonier
 */
public interface Birthmark extends Serializable, Streamable<Element>, Namer {
    Metadata metadata();

    Stream<Element> stream();

    ContainerType containerType();

    long size();

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
        visitor.visitElementCount(size());
        var index = Cursor.of(size());
        stream().forEach(element -> visitor.visitElement(element, index.incrementIsLast()));
        visitor.visitEnd();
    }
}
