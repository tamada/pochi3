package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.utils.Namer;

import java.util.Objects;

public interface PairerBuilder<T extends Namer> extends TaskBuilder<Pairer<T>, PairerType> {
    PairerType type();

    Pairer<T> build(Configuration config);

    Pairer<T> build(Configuration config, Relationer relationer);

    default boolean matchType(String name) {
        return Objects.equals(name, type().type());
    }
}
