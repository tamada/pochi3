package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.sink.DataSink;

import java.nio.file.Path;

public interface DataSinkFactory {
    DataSink create(Path path);

    boolean isTarget(Path path);

    static DataSinkFactory instance() {
        return new DefaultDataSinkFactory();
    }
}
