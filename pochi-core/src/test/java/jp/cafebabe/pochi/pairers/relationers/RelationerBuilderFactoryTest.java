package jp.cafebabe.pochi.pairers.relationers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RelationerBuilderFactoryTest {
    private RelationerBuilderFactory factory = new RelationerBuilderFactory();
    @Test
    public void testFully() {
        var relationer = factory.builder("fully")
                .orElseGet(() -> new FullyMatchRelationer.Builder()).build(null);
        assertEquals("jp.cafebabe.pochi.pairers.relationers.FullyMatchRelationer", relationer.getClass().getName());
        assertTrue(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }

    @Test
    public void testClassName() {
        var relationer = factory.builder("classname")
                .orElseGet(() -> new SimpleClassNameRelationer.Builder()).build(null);
        assertEquals("jp.cafebabe.pochi.pairers.relationers.SimpleClassNameRelationer", relationer.getClass().getName());
        assertTrue(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertTrue(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }

    @Test
    public void testOther() {
        var relationer = factory.builder("other")
                .get().build(null);
        assertEquals("jp.cafebabe.pochi.pairers.relationers.NeverRelationer", relationer.getClass().getName());
        assertFalse(relationer.isRelate("SomeClassName", "SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.cafebabe.pochi.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName", "jp.otherpackage.SomeClassName"));
        assertFalse(relationer.isRelate("jp.cafebabe.pochi.SomeClassName1", "jp.cafebabe.pochi.SomeClassName2"));
    }
}