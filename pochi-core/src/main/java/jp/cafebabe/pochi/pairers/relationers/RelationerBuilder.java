package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;

import java.util.Map;

public class RelationerBuilder {
    private static Map<String, Relationer> relationers =
            Map.of("fully", new FullyMatchRelationer(),
                "classname", new ClassNameRelationer());

    public static Relationer build(Configuration config) {
        return build(config.value("pairer.relationer"));
    }

    public static Relationer build(String type) {
        return relationers.getOrDefault(type,
                (n1, n2) -> false);
    }
}
