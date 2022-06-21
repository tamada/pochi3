package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import org.junit.jupiter.api.Test;

import static jp.cafebabe.pochi.comparators.BirthmarkBuilder.buildBirthmarks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosineComparatorTest {
    private Configuration config = Configuration.defaultConfig();
    private ComparatorBuilder builder = new CosineComparator.Builder();

    @Test
    public void testBuilder() {
        assertEquals("cosine similarity (vector)", builder.description());
        assertEquals(CosineComparator.TYPE, builder.type());
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
    public void testEitherZero() throws Exception {
        var comparator = builder.build(config);
        var b1 = buildBirthmarks().stream().findFirst().get();
        var b2 = buildBirthmarks("a", "b", "c").stream().findFirst().get();
        var result = comparator.similarity(Pair.of(b1, b2));
        assertEquals(new Similarity(0d), comparator.similarity(Pair.of(b1, b2)).get());
        assertEquals(new Similarity(0d), comparator.similarity(Pair.of(b2, b1)).get());
    }

    @Test
    public void testSimilarity() throws Exception {
        var comparator = builder.build(config);
        var b1 = buildBirthmarks("android".split("")).stream().findFirst().get();
        var b2 = buildBirthmarks("ipodtouch".split("")).stream().findFirst().get();
        var result = comparator.similarity(Pair.of(b1, b2));
        assertTrue(new Similarity(0.5025189).isCloseTo(result.get(), 1E-6));
    }
}
