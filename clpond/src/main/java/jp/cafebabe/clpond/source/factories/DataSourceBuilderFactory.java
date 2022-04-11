package jp.cafebabe.clpond.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSourceBuilderFactory {
    private final List<DataSourceBuilder> factories = new ArrayList<>();

    public DataSourceBuilderFactory(){
        factories.add(new JarFileDataSourceBuilder());
        factories.add(new WarFileDataSourceBuilder());
        factories.add(new ClassFileDataSourceBuilder());
        factories.add(new DirectoryDataSourceBuilder());
        factories.add(new PlainFileDataSourceBuilder());
    }

    public Optional<DataSourceBuilder> find(Path path) throws IOException{
        return find(path, FileSystems.getDefault());
    }

    public Optional<DataSourceBuilder> find(Path path, FileSystem system) throws IOException{
        return find(path, system, system.provider());
    }

    private Optional<DataSourceBuilder> find(Path path, FileSystem system,
                                             FileSystemProvider provider) throws IOException{
        Class<BasicFileAttributes> clazz = BasicFileAttributes.class;
        return find(path, system, provider.readAttributes(path, clazz));
    }

    private Optional<DataSourceBuilder> find(Path path, FileSystem system,
                                             BasicFileAttributes attr) {
        return factories.stream()
                .filter(factory -> factory.isTarget(path, system, attr))
                .findFirst();
    }
}
