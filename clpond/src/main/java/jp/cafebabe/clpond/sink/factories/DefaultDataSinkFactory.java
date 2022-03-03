package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class DefaultDataSinkFactory implements DataSinkFactory{
    private List<DataSinkFactory> factories = new ArrayList<>();

    public DefaultDataSinkFactory(){
        register(new GenericDataSinkFactory(path -> PathHelper.endsWith(path, ".jar"), path -> new JarFileDataSink(path)));
        register(new GenericDataSinkFactory(path -> PathHelper.endsWith(path, ".war"),   path -> new WarFileDataSink(path)));
        register(new GenericDataSinkFactory(path -> PathHelper.endsWith(path, ".class"), path -> new ClassFileDataSink(path)));
        register(new GenericDataSinkFactory(path -> true,                                path -> new DirectoryDataSink(path)));
    }

    public boolean isTarget(Path path){
        return path != null;
    }
    
    public DataSinkFactory factory(Path path){
        return factories.stream()
                .filter(factory -> factory.isTarget(path))
                .findFirst().get();
    }

    public DataSink create(Path path){
        return factory(path).create(path);
    }

    private void register(GenericDataSinkFactory factory){
        factories.add(factory);
    }
}
