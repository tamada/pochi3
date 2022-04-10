package jp.cafebabe.pochi.utils.spi;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;

public interface NoImplementationService extends TaskBuilder<String, Service.Type> {
    Service.Type type();

    default String build(Configuration config) {
        return type().string();
    }

    default boolean matchType(String type) {
        return true;
    }
}
