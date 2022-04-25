package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.io.BirthmarkParser;

import java.util.Arrays;
import java.util.stream.Collectors;

class BirthmarkBuilder {
    static final Birthmarks buildBirthmarks(String... elements) throws Exception {
        String json = """
            [{"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":[%s]}]
            """.trim();
        BirthmarkParser parser = new BirthmarkParser();
        return parser.parse(String.format(json, Arrays.stream(elements)
                .map(s -> "\"" + s + "\"").collect(Collectors.joining(","))));
    }
}
