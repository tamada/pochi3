package jp.cafebabe.pochi.pairers.relationers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelationerBuilderTest {
    @Test
    public void testFully() {
        var relationer = RelationerBuilder.build("fully");
        assertTrue(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }

    @Test
    public void testClassName() {
        var relationer = RelationerBuilder.build("classname");
        assertTrue(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }

    @Test
    public void testOther() {
        var relationer = RelationerBuilder.build("other");
        assertFalse(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }
}