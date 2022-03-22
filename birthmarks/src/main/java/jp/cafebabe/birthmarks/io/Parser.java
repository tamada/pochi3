package jp.cafebabe.birthmarks.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.vavr.control.Try;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public interface Parser<T> {
    T parse(JsonElement element);

    default T parse(URL url) throws IOException {
        return parse(url.openStream());
    }

    default T parse(InputStream in) throws IOException {
        return parse(new InputStreamReader(in));
    }

    default T parse(Reader in) throws IOException {
        return Try.of(() -> parse(JsonParser.parseReader(in)))
                .getOrElseThrow(t -> new IOException(t));
    }

    default T parse(String jsonString) throws IOException {
        return Try.of(() -> parse(JsonParser.parseString(jsonString)))
                .getOrElseThrow(t -> new IOException(t));
    }
}
