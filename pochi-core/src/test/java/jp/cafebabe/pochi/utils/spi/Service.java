package jp.cafebabe.pochi.utils.spi;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.utils.Stringer;

public interface Service extends TaskBuilder<String, Service.Type> {
    Type type();

    default String build(Configuration config) {
        return type().string();
    }

    default boolean matchType(String type) {
        return true;
    }

    static final class Type implements Stringer {
        private String type;
        private Type(String type) {
            this.type = type;
        }
        public static Type of(String type) {
            return new Type(type);
        }
        public String string() {
            return type;
        }
    }
}
