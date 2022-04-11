package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.sink.DataSink;

import java.nio.file.Path;

public interface DataSinkBuilder {
    DataSink build(Path path);

    boolean isTarget(Path path);

    static DataSinkBuilder instance() {
        return new DefaultDataSinkBuilder();
    }
}
