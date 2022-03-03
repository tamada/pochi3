package jp.cafebabe.clpond.source.factories;


import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.PlainFileDataSource;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class PlainFileDataSourceFactory implements DataSourceFactory{
    public PlainFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws IOException {
        return new PlainFileDataSource(path);
    }
}
