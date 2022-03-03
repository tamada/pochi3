package jp.cafebabe.clpond.sink;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.Entry;

public class DirectoryDataSink extends ClassFileDataSink {

    public DirectoryDataSink(Path path){
        super(path);
    }

    @Override
    public void consume(InputStream in, Entry entry) throws IOException {
        Path outputPath = path().resolve(createPath(entry));
        createDirectories(outputPath.getParent());
        consume(in, outputPath);
    }

    private void consume(InputStream in, Path path) throws IOException{
        Try.withResources(() -> Files.newOutputStream(path))
                .of(out -> in.transferTo(out))
                .getOrElseThrow(e -> new IOException(e.getMessage()));
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return Paths.get(toJVMClassName(entry) + ".class");
        return Paths.get(entry.path().toString());
    }
}
