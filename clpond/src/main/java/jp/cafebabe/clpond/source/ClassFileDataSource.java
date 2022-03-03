package jp.cafebabe.clpond.source;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.entities.PathEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ClassFileDataSource extends AbstractDataSource implements PathResolver{
    private ClassName name = null;

    public ClassFileDataSource(Path path){
        super(path);
    }

    @Override
    public Stream<Entry> stream() {
        return Stream.of(new PathEntry(base(), this));
    }

    @Override
    public void close() {
        // do nothing.
    }

    @Override
    public InputStream openStream(Path path) throws IOException {
        return Files.newInputStream(path);
    }

    @Override
    public ClassName parseClassName(Path path) {
        if(name == null)
            name = extractClassName(path).getOrElseThrow(e -> new InternalError(e));
        return name;
    }

    private Either<IOException, ClassName> extractClassName(Path path) {
        return Try.of(() -> resolveClassName(path))
                .toEither()
                .mapLeft(e -> (IOException)e);
    }
}
