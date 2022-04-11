package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.DirectoryDataSource;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class DirectoryDataSourceBuilder implements DataSourceBuilder {
    public DirectoryDataSourceBuilder(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return attributes.isDirectory();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws IOException {
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported.");
        return new DirectoryDataSource(path);
    }
}
