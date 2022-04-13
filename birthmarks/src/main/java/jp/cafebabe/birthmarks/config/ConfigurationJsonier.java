package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.io.Jsonier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationJsonier implements Jsonier<Configuration> {
    private String properties(Stream<Pair<String, String>> stream) {
        return stream.map(p -> String.format("\"%s\":\"%s\"", p.left(), p.right()))
                .collect(Collectors.joining(","));
    }

    private String rules(Stream<Rule> stream) {
        var rj = new RuleJsonier();
        return stream.map(r -> rj.toJson(r))
                .collect(Collectors.joining(","));
    }

    @Override
    public String toJson(Configuration target) {
        return String.format("{\"properties\":{%s},\"rules\":[%s]}",
                properties(target.properties()), rules(target.rules()));
    }
}
