package jp.cafebabe.clpond.sink;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class JarFileDataSink extends AbstractDataSink {
    private final FileSystem system;
    private final Path base;

    public JarFileDataSink(Path path){
        this.system = DataSinkHelper.buildFileSystem(path);
        this.base = system.getPath("/");
    }

    JarFileDataSink(FileSystem system, String base){
        this.system = system;
        this.base = system.getPath(base);
    }

    @Override
    public void close() throws IOException {
        system.close();
    }

    @Override
    public void consume(InputStream in, Entry entry) {
        Path outputPath = base.resolve(createPath(entry));
        DirectoryMaker.mkdirs(outputPath.getParent(), system);
        consume(in, outputPath);
    }

    private void consume(InputStream in, Path path) {
        Try.withResources(() -> DataSinkHelper.newOutputStream(system, path))
                .of(in::transferTo);
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return system.getPath(toJVMClassName(entry) + ".class");
        return system.getPath(entry.path().toString());
    }
}
