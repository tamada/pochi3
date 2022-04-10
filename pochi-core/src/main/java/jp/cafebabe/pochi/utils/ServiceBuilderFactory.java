package jp.cafebabe.pochi.utils;

import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.utils.Stringer;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class ServiceBuilderFactory<B extends TaskBuilder<TB, T>, T extends Stringer, TB> implements BuilderFactory<B, T> {
    private final List<B> list;

    public ServiceBuilderFactory(Class<B> clazz) {
        var loader = ServiceLoader.load(clazz);
        this.list = loader.stream()
                .map(ServiceLoader.Provider::get)
                .toList();
    }

    public Optional<B> builder(String type) {
        return builders()
                .filter(t -> t.matchType(type))
                .findFirst();
    }

    public Stream<T> availables() {
        return builders()
                .map(TaskBuilder::type);
    }

    public Stream<B> builders() {
        return list.stream();
    }
}
