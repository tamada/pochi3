package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.JarFileDataSource;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class JarFileDataSourceFactory implements DataSourceFactory{
    public JarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".jar")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws IOException {
        try{ return buildImpl(path, system); }
        catch(IOException e){
            throw new UnsupportedDataSourceException(e.getMessage()); 
        }
    }

    private DataSource buildImpl(Path path, FileSystem system) throws IOException{
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported");
        return buildDataSourceImpl(path);
    }

    private DataSource buildDataSourceImpl(Path path) throws IOException{
        ClassLoader loader = getClass().getClassLoader();
        FileSystem jarSystem = FileSystems.newFileSystem(path, loader);
        return buildDataSource(path, jarSystem);
    }

    DataSource buildDataSource(Path base, FileSystem system){
        return new JarFileDataSource(base, system);
    }
}
