package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Writer;
import java.util.stream.Stream;

public class ConfigurationMarshaller {
    private Marshaller marshaller;

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
        marshaller.marshal("{\"properties\":{");
        marshalProperties(config.properties());
        marshaller.marshal("},\"rules\":");
        marshalRules(config.rules());
        return marshaller.marshal("}");
    }

    private boolean marshalRules(Stream<Rule> stream) {
        return marshaller.marshalStream(stream, this::marshalRule);
    }

    private boolean marshalRule(Rule rule) {
        return marshaller.marshal(rule.toJson());
    }

    private void marshalProperties(Stream<Pair<String, String>> stream) {
        marshaller.marshalStream(stream, pair ->
                marshaller.marshalKeyAndValue(pair.left(), pair.right()));
    }
}
