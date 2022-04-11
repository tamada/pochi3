package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class DefaultDataSinkBuilder implements DataSinkBuilder {
    private final List<DataSinkBuilder> factories = new ArrayList<>();

    public DefaultDataSinkBuilder(){
        register(new GenericDataSinkBuilder(path -> PathHelper.endsWith(path, ".jar"), JarFileDataSink::new));
        register(new GenericDataSinkBuilder(path -> PathHelper.endsWith(path, ".war"),   WarFileDataSink::new));
        register(new GenericDataSinkBuilder(path -> PathHelper.endsWith(path, ".class"), ClassFileDataSink::new));
        register(new GenericDataSinkBuilder(path -> true,                                DirectoryDataSink::new));
    }

    public boolean isTarget(Path path){
        return path != null;
    }
    
    public DataSinkBuilder factory(Path path){
        return factories.stream()
                .filter(factory -> factory.isTarget(path))
                .findFirst().get();
    }

    public DataSink build(Path path){
        return factory(path).build(path);
    }

    private void register(GenericDataSinkBuilder factory){
        factories.add(factory);
    }
}
