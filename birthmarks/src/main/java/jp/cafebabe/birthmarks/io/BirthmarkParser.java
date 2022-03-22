package jp.cafebabe.birthmarks.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.utils.JsonUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BirthmarkParser implements Parser<Birthmarks> {
    private BirthmarkUnmarshaller unmarshaller = new BirthmarkUnmarshaller();

    public Birthmarks parse(JsonElement element) {
        List<Either<Throwable, Birthmark>> list = parseJson(JsonUtil.stream(element.getAsJsonArray()));
        return new Birthmarks(list.stream());
    }

    private List<Either<Throwable, Birthmark>> parseJson(Stream<JsonElement> stream) {
        return stream.map(e -> parseBirthmark(e))
                .collect(Collectors.toList());
    }

    private Either<Throwable, Birthmark> parseBirthmark(JsonElement element) {
        return Try.of(() -> unmarshaller.unmarshal(element.getAsJsonObject()))
                .toEither();
    }
}
