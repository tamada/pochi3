package jp.cafebabe.birthmarks.extractors;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.impl.Converter;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.objectweb.asm.ClassVisitor;

public interface Extractor {
    default Birthmarks extract(DataSource source) {
        return extract(source, ContainerType.List);
    }

    default Birthmarks extract(DataSource source, ContainerType ct) {
        return new Birthmarks(source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, ct)));
    }

    Either<Throwable, Birthmark> extractEach(Entry entry);

    default Either<Throwable, Birthmark> extractEach(Entry entry, ContainerType ct) {
        var either = extractEach(entry);
        return switch(ct) {
            case Set -> either.map(Converter::toSet);
            case Vector -> either.map(Converter::toFrequency);
            case List  -> either.map(Converter::toList);
            case Graph -> either;
        };
    }

    PochiClassVisitor visitor(ClassVisitor visitor);
}
