package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.source.DataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

public interface DataSourceBuilder {
    boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes);

    default boolean isTarget(Path path, FileSystem system){
        Optional<BasicFileAttributes> attributes = PathHelper.readAttributes(path, system);
        return attributes.isPresent() && 
                isTarget(path, system, attributes.get());
    }

    default boolean isTarget(Path path){
        FileSystem system = FileSystems.getDefault();
        return isTarget(path, system);
    }

    DataSource build(Path path, FileSystem system) throws IOException;

    default DataSource build(Path path) throws IOException{
        FileSystem system = FileSystems.getDefault();
        return build(path, system);
    }

    default DataSource build(File file) throws IOException{
        return build(file.toPath());
    }

    static DataSourceBuilder instance() {
        return new DefaultDataSourceBuilder();
    }
}
