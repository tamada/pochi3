package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.io.BirthmarkParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiceIndexComparatorTest {
    private Configuration config = Configuration.defaultConfig();
    private ComparatorBuilder builder = new DiceIndexComparator.Builder();

    @Test
    public void testBuilder() {
        assertEquals("dice index", builder.description());
        assertEquals(DiceIndexComparator.TYPE, builder.type());
    }

    @Test
    public void testBothZero() throws Exception {
        var comparator = builder.build(config);
        var b1 = buildBirthmarks().stream().findFirst().get();
        var b2 = buildBirthmarks().stream().findFirst().get();
        var result = comparator.similarity(Pair.of(b1, b2));
        assertEquals(new Similarity(1d), result.get());
    }

    @Test
    public void testSimilarity() throws Exception {
        var comparator = builder.build(config);
        var b1 = buildBirthmarks("a", "b", "c").stream().findFirst().get();
        var b2 = buildBirthmarks("a", "b").stream().findFirst().get();
        var result = comparator.similarity(Pair.of(b1, b2));
        assertEquals(new Similarity(2.0 * 2 / 5), result.get());
    }

    private Birthmarks buildBirthmarks(String... elements) throws Exception {
        String json = """
            [{"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":[%s]}]
            """.trim();
        BirthmarkParser parser = new BirthmarkParser();
        return parser.parse(String.format(json, Arrays.stream(elements)
                .map(s -> "\"" + s + "\"").collect(Collectors.joining(","))));
    }
}
