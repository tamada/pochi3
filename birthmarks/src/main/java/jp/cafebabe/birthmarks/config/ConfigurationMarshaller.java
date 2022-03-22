package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Writer;
import java.util.stream.Stream;

public class ConfigurationMarshaller {
    private final Marshaller marshaller;

    private ConfigurationMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public static ConfigurationMarshaller of(Writer out) {
        return of(Marshaller.of(out));
    }

    public static ConfigurationMarshaller of(Marshaller marshaller) {
        return new ConfigurationMarshaller(marshaller);
    }

    public boolean marshal(Configuration config) {
        return Stream.of(marshaller.marshal("{\"properties\":{"),
            marshalProperties(config.properties()),
            marshaller.marshal("},\"rules\":"),
            marshalRules(config.rules()), marshaller.marshal("}"))
                .reduce(true, (a, b) -> a && b);
    }

    private boolean marshalRules(Stream<Rule> stream) {
        return marshaller.marshalStream(stream, this::marshalRule);
    }

    private boolean marshalRule(Rule rule) {
        return marshaller.marshal(rule.toJson());
    }

    private boolean marshalProperties(Stream<Pair<String, String>> stream) {
        return marshaller.marshalStream(stream, pair ->
                marshaller.marshalKeyAndValue(pair.left(), pair.right()));
    }
}
