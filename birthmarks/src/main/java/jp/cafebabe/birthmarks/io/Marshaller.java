package jp.cafebabe.birthmarks.io;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Cursor;

import java.io.Writer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Marshaller {
    boolean marshal(String value);

    default boolean marshalArray(String... values) {
        return Arrays.stream(values)
                .map(this::marshal)
                .reduce(true, (a, b) -> a && b);
    }

    default boolean marshalKeyAndValue(String key, String value) {
        return marshal(String.format("\"%s\":\"%s\"", key, value));
    }

    default <T> boolean marshalStream(Stream<T> stream, Function<T, Boolean> action) {
        var i = Cursor.of(Integer.MAX_VALUE);
        return stream.map(item -> {
            if(i.incrementIsFirst())
                marshal(",");
            return action.apply(item);
        }).reduce(true, (a, b) -> a && b);
    }

    static Marshaller of(Writer out) {
        return value -> Try.run(() -> out.write(value))
                        .isSuccess();
    }
}
