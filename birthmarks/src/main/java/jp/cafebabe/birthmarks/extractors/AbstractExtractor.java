package jp.cafebabe.birthmarks.extractors;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.clpond.entities.Entry;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractExtractor implements Extractor {
    private Configuration config;

    public AbstractExtractor(Configuration config) {
        this.config = config;
    }

    public Configuration configuration() {
        return config;
    }

    @Override
    public Either<Throwable, Birthmark> extractEach(Entry entry) {
        return Try.withResources(() -> entry.openStream())
                .of(in -> extractImpl(entry, in))
                .toEither();
    }

    private Birthmark extractImpl(Entry entry, InputStream in) throws IOException, ExtractionException {
        var visitor = visitor(new ClassWriter(0));
        new ClassReader(in).accept(visitor, 0);
        return visitor.build(entry);
    }

    @Override
    public abstract PochiClassVisitor visitor(ClassVisitor visitor);
}
