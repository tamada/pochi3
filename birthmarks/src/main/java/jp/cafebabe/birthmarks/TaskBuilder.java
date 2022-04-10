package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.utils.Stringer;

import java.util.Objects;

public interface TaskBuilder<B, T extends Stringer> {
    T type();

    String description();

    B build(Configuration config);

    default boolean matchType(String name) {
        return Objects.equals(type().string(), name);
    }
}
