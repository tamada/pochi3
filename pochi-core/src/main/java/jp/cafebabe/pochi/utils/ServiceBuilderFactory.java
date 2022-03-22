package jp.cafebabe.pochi.utils;

import jp.cafebabe.birthmarks.TaskBuilder;

import java.util.Optional;
import java.util.ServiceLoader;

public class ServiceBuilderFactory<K, B extends TaskBuilder<K>> {
    private final ServiceLoader<B> loader;

    public ServiceBuilderFactory(Class<B> clazz) {
        loader = ServiceLoader.load(clazz);
    }

    public Optional<B> builder(String type) {
        return loader.stream()
                .map(ServiceLoader.Provider::get)
                .filter(t -> t.matchType(type))
                .findFirst();
    }
}
