package jp.cafebabe.birthmarks.config;

import java.util.Map;

public class Configuration {
    private Map<String, String> properties;

    public Configuration() {
    }

    public String value(String key) {
        return properties.get(key);
    }

    public String value(String key, String defaultValue) {
        return properties.getOrDefault(key, defaultValue);
    }
}
