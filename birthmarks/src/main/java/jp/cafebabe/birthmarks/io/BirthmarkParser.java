package jp.cafebabe.birthmarks.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.extractors.Birthmark;
import jp.cafebabe.birthmarks.extractors.Birthmarks;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BirthmarkParser {
    private BirthmarkUnmarshaller unmarshaller = new BirthmarkUnmarshaller();

    public Birthmarks parse(InputStream in) throws IOException, JsonSyntaxException {
        return parse(new InputStreamReader(in));
    }

    public Birthmarks parse(Reader in) throws IOException, JsonSyntaxException {
        return parse(JsonParser.parseReader(in));
    }

    public Birthmarks parse(String jsonData) throws JsonSyntaxException {
        return parse(JsonParser.parseString(jsonData));
    }

    private Birthmarks parse(JsonElement element) throws JsonSyntaxException {
        List<Either<Throwable, Birthmark>> list = parseJson(JsonUtil.stream(element.getAsJsonArray()));
        throwExceptionIfNeeded(list);
        return Birthmarks.of(list.stream().filter(Either::isRight)
                .map(Either::get));
    }

    private void throwExceptionIfNeeded(List<Either<Throwable, Birthmark>> list) throws JsonSyntaxException {
        List<Throwable> exceptions =  list.stream().filter(Either::isLeft)
                .map(Either::getLeft).collect(Collectors.toList());
        if(!exceptions.isEmpty())
            throw new JsonSyntaxException(exceptions.stream().map(Throwable::getMessage).collect(Collectors.joining(", ")));
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
