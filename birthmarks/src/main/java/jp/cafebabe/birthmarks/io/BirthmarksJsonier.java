package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.Birthmarks;

import java.io.StringWriter;
import java.util.stream.Collectors;

public class BirthmarksJsonier implements Jsonier<Birthmarks> {
    @Override
    public String toJson(Birthmarks target) {
        var jsonier = new BirthmarkJsonier();
        var out = new StringWriter();
        out.write("[");
        out.write(target.stream()
                .map(t -> jsonier.toJson(t))
                .collect(Collectors.joining(",")));
        out.write("]");
        return out.toString();
    }
}
