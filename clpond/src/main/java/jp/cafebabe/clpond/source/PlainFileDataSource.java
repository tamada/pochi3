package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.entities.PathEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PlainFileDataSource extends AbstractDataSource implements PathResolver{
    public PlainFileDataSource(Path path){
        super(path);
    }

    @Override
    public Stream<Entry> stream() {
        return Stream.of(new PathEntry(base(), this));
    }

    @Override
    public void close() throws IOException {
        // do nothing.
    }

    @Override
    public InputStream openStream(Path path) throws IOException {
        return Files.newInputStream(path);
    }

    @Override
    public ClassName parseClassName(Path path) {
        return null;
    }
}
