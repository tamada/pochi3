package jp.cafebabe.pochi.utils.spi;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;

public interface NoImplementationService extends TaskBuilder<String, String> {
    String type();

    default String build(Configuration config) {
        return type();
    }

    default boolean matchType(String type) {
        return true;
    }
}
