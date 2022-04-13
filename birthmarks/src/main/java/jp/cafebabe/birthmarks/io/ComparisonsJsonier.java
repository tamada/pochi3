package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.comparators.Comparisons;

import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Collectors;

public class ComparisonsJsonier implements Jsonier<Comparisons> {
    @Override
    public String toJson(Comparisons target) {
        StringWriter out = new StringWriter();
        var jsonier = new ComparisonJsonier();
        out.write("[");
        out.write(target.stream()
                        .map(c -> jsonier.toJson(c))
                        .collect(Collectors.joining(",")));
        out.write("]");
        return out.toString();
    }
}
