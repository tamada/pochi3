package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;

import java.util.Map;

public class RelationerFactory {
    private final Map<String, Relationer> relationers =
            Map.of("fully", new FullyMatchRelationer(),
                "classname", new ClassNameRelationer());

    public Relationer build(Configuration config) {
        return build(config.value("pairer.relationer"));
    }

    public Relationer build(String type) {
        return relationers.getOrDefault(type,
                (n1, n2) -> false);
    }
}
