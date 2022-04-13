package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.io.Jsonier;

public class RuleJsonier implements Jsonier<Rule> {
    @Override
    public String toJson(Rule rule) {
        return String.format("{\"type\":\"%s\",\"pattern\":\"%s\"}",
                rule.position().name(), rule.snippet().value());
    }
}
