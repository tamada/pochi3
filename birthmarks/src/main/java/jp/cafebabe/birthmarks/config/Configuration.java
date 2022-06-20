package jp.cafebabe.birthmarks.config;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.comparators.Pair;

import java.util.Map;
import java.util.stream.Stream;

public class Configuration {
    private final Map<String, String> properties;
    private final Rules rules;

    Configuration(Rules rules, Map<String, String> props) {
        this.rules = rules;
        this.properties = props;
    }

    public void put(String key, String value) {
        properties.put(key, value);
    }

    public Stream<Pair<String, String>> properties() {
        return properties.entrySet().stream()
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()));
    }

    public boolean match(String pattern) {
        return rules.match(pattern);
    }

    public Stream<Rule> rules() {
        return rules.stream();
    }

    public String value(String key) {
        return properties.get(key);
    }

    public String value(String key, String defaultValue) {
        return properties.getOrDefault(key, defaultValue);
    }

    public static Configuration defaultConfig(Map<String, String> properties) {
        return Try.of(() -> new ConfigurationParser()
                        .parse(Configuration.class.getResourceAsStream("/resources/config.json"), properties))
                .get();
    }

    public static Configuration defaultConfig() {
        return defaultConfig(Map.of());
    }
}
