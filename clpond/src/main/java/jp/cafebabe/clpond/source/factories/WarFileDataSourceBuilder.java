package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.WarFileDataSource;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class WarFileDataSourceBuilder extends JarFileDataSourceBuilder {
    public WarFileDataSourceBuilder(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".war")
                && attributes.isRegularFile();
    }

    @Override
    DataSource buildDataSource(Path path, FileSystem system){
        return new WarFileDataSource(path, system);
    }
}
