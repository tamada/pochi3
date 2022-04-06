package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;

public interface TaskBuilder<T, K> {
    K type();

    T build(Configuration config);

    boolean matchType(String name);
}
