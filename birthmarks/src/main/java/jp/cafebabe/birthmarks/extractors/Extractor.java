package jp.cafebabe.birthmarks.extractors;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.Results;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.objectweb.asm.ClassVisitor;

public interface Extractor {
    default Birthmarks extract(DataSource source) {
        return new Birthmarks(source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry)));
    }

    Either<Throwable, Birthmark> extractEach(Entry entry);

    PochiClassVisitor visitor(ClassVisitor visitor);
}
