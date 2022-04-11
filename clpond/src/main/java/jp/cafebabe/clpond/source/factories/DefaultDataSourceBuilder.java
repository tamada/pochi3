package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.source.DataSource;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

class DefaultDataSourceBuilder implements DataSourceBuilder {
    private final DataSourceBuilderFactory factories = new DataSourceBuilderFactory();

    @Override
    public DataSource build(Path path, FileSystem system) throws IOException {
         return build(factories.find(path, system), path);
    }

    private DataSource build(Optional<DataSourceBuilder> source, Path path) throws IOException{
        return source.orElseThrow(
                () -> new UnsupportedDataSourceException(path.toString()))
                .build(path);
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes) {
        return path != null;
    }
}
