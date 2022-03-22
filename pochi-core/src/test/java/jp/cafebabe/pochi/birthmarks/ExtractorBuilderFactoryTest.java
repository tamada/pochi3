package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractorBuilderFactoryTest {
    @Test
    public void testCreatedClass() {
        var factory = new ExtractorBuilderFactory();
        var b1 = factory.builder("uc");
        assertTrue(b1.isPresent());
        assertEquals(UsedClassesExtractorBuilder.class, b1.get().getClass());

        var b2 = factory.builder("1-gram");
        assertTrue(b2.isPresent());
        assertEquals(KGramBasedExtractorBuilder.class, b2.get().getClass());
    }

    @Test
    public void testKGram() {
        var factory = new ExtractorBuilderFactory();
        var b1 = factory.builder("2-gram");
        assertEquals(BirthmarkType.of("2-gram"), b1.get().type());
    }
}
