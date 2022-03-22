package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.entities.PathEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.stream.Stream;

public class JarFileDataSource extends AbstractDataSource implements PathResolver{
    private final FileSystem system;

    public JarFileDataSource(Path path, FileSystem system) {
        super(path);
        this.system = system;
    }

    @Override
    public Stream<Entry> stream(){
        List<Path> list = getAllFilesFromPaths(getRootPaths());
        return list.stream()
                .map(this::toEntry);
    }

    private List<Path> getAllFilesFromPaths(Path[] paths){
        FileSystemProvider provider = system.provider();
        return new DirectoryTraverser()
                .traverse(provider, paths);
    }

    private Path[] getRootPaths(){
        return StreamHelper
                .stream(system.getRootDirectories())
                .toArray(Path[]::new);
    }

    @Override
    public ClassName parseClassName(Path path){
        String name = parseClassName(path.toString());
        return new ClassName(name);
    }

    @Override
    public InputStream openStream(Path entry) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newInputStream(entry);
    }

    private Entry toEntry(Path path){
        return new PathEntry(path, this);
    }

    @Override
    public void close() throws IOException{
        system.close();
    }
}
