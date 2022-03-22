package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;

public interface TaskBuilder<T> {
    T build(Configuration config);

    boolean matchType(String name);
}
