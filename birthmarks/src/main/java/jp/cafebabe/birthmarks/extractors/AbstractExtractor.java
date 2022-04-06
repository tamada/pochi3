package jp.cafebabe.birthmarks.extractors;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.events.BirthmarkEvent;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractExtractor extends Task implements Extractor {
    private final Configuration config;

    public AbstractExtractor(Configuration config) {
        this.config = config;
    }

    public Configuration configuration() {
        return config;
    }

    @Override
    public Birthmarks extract(DataSource source) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.Before, source));
        var result = new Birthmarks(source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(this::extractEach));
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.After, source));
        return result;
    }

    @Override
    public Either<Throwable, Birthmark> extractEach(Entry entry) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.BeforeEach, entry));
        var result = Try.withResources(entry::openStream)
                .of(in -> extractImpl(entry, in))
                .toEither();
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.AfterEach, entry));
        return result;
    }

    private Birthmark extractImpl(Entry entry, InputStream in) throws IOException, ExtractionException {
        var visitor = visitor(new ClassWriter(0));
        new ClassReader(in).accept(visitor, 0);
        return visitor.build(entry);
    }

    @Override
    public abstract PochiClassVisitor visitor(ClassVisitor visitor);
}
