package jp.cafebabe.birthmarks.io;

import com.google.gson.JsonElement;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.events.BirthmarkEvent;
import jp.cafebabe.birthmarks.utils.JsonUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BirthmarkParser extends Task implements Parser<Birthmarks> {
    private final BirthmarkUnmarshaller unmarshaller = new BirthmarkUnmarshaller();

    public Birthmarks parse(JsonElement element) {
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Parsing, BirthmarkEvent.Phase.Before, element));
        List<Either<Throwable, Birthmark>> list = parseJson(JsonUtil.stream(element.getAsJsonArray()));
        var result = new Birthmarks(list.stream());
        fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Parsing, BirthmarkEvent.Phase.After, element));
        return result;
    }

    private List<Either<Throwable, Birthmark>> parseJson(Stream<JsonElement> stream) {
        return stream.map(this::parseBirthmark)
                .collect(Collectors.toList());
    }

    private Either<Throwable, Birthmark> parseBirthmark(JsonElement element) {
        return Try.of(() -> unmarshaller.unmarshal(element.getAsJsonObject()))
                .toEither();
    }
}
