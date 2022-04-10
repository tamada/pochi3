package jp.cafebabe.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

public interface BuilderFactory<B, T> {
    Optional<B> builder(String type);

    Stream<B> builders();

    Stream<T> availables();
}
