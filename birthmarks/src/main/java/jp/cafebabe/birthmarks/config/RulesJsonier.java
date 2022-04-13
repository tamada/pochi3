package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.io.Jsonier;

import java.util.stream.Collectors;

public class RulesJsonier implements Jsonier<Rules> {
    @Override
    public String toJson(Rules target) {
        var rj = new RuleJsonier();
        return String.format("[%s]", target.stream()
                .map(rj::toJson)
                .collect(Collectors.joining(",")));
    }
}
