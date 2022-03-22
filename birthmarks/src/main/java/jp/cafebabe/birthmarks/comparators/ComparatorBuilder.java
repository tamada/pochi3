package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;

import java.util.Objects;

public interface ComparatorBuilder extends TaskBuilder<Comparator> {
    ComparatorType type();

    Comparator build(Configuration config);

    default boolean matchType(String name) {
        return Objects.equals(name, type().type());
    }
}
