package jp.cafebabe.birthmarks.io;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.extractors.Birthmark;
import jp.cafebabe.birthmarks.extractors.Birthmarks;

import java.io.Writer;

public interface Marshaller {
    boolean marshal(String value);

    default void marshal(Birthmarks birthmarks) {
        marshal("[");
        birthmarks.stream().forEach(birthmark -> marshal(birthmark));
        marshal("]");
    }

    default void marshal(String key, String value) {
        marshal(String.format("\"%s\":\"%s\"", key, value));
    }

    default void marshal(Birthmark birthmark) {
        birthmark.accept(new BirthmarkMarshaller(this));
    }

    static Marshaller of(Writer out) {
        return value -> Try.run(() -> out.write(value))
                        .isSuccess();
    }
}
