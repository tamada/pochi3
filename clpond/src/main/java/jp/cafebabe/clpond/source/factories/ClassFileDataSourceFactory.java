package jp.cafebabe.clpond.source.factories;


import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.source.ClassFileDataSource;
import jp.cafebabe.clpond.source.DataSource;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class ClassFileDataSourceFactory implements DataSourceFactory{
    public ClassFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".class")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws IOException {
        return new ClassFileDataSource(path);
    }
}
