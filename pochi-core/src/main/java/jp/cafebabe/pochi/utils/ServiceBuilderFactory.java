package jp.cafebabe.pochi.utils;

import jp.cafebabe.birthmarks.TaskBuilder;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class ServiceBuilderFactory<K, T> {
    private final ServiceLoader<? extends TaskBuilder<K, T>> loader;

    public ServiceBuilderFactory(Class<? extends TaskBuilder<K, T>> clazz) {
        loader = ServiceLoader.load(clazz);
    }

    public Optional<? extends TaskBuilder<K, T>> builder(String type) {
        return builders()
                .filter(t -> t.matchType(type))
                .findFirst();
    }

    public Stream<T> availables() {
        return builders()
                .map(item -> item.type());
    }

    public Stream<? extends TaskBuilder<K, T>> builders() {
        return loader.stream()
                .map(ServiceLoader.Provider::get);
    }
}
