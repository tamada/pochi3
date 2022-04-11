package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.sink.DataSink;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;

class GenericDataSinkBuilder implements DataSinkBuilder {
    private final Predicate<Path> predicate;
    private final Function<Path, DataSink> function;

    public GenericDataSinkBuilder(Predicate<Path> predicate, Function<Path, DataSink> function){
        this.predicate = predicate;
        this.function = function;
    }
    
    @Override
    public boolean isTarget(Path path){
        return predicate.test(path);
    }

    @Override
    public DataSink build(Path path){
        return function.apply(path);
    }
}
