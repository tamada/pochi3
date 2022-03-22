package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.utils.Jsonable;

import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Stream;

public class Configuration implements Jsonable {
    private Map<String, String> properties;
    private Rules rules;

    Configuration(Rules rules, Map<String, String> props) {
        this.rules = rules;
        this.properties = props;
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

    @Override
    public String toJson() {
        StringWriter out = new StringWriter();
        ConfigurationMarshaller.of(out).marshal(this);
        return out.toString();
    }
}
