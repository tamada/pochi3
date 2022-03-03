package jp.cafebabe.clpond.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClassNameTest {
    private ClassName name1;
    private ClassName name2;

    @BeforeEach
    public void setUp(){
        name1 = new ClassName("jp.cafebabe.pochi.kunai.entries.ClassNameTest");
        name2 = new ClassName("jp.cafebabe/pochi/kunai/entries/NameTest");
    }

    @Test
    public void testBasic(){
        assertEquals(new Name("jp.cafebabe.pochi.kunai.entries.ClassNameTest"), name1);
        assertNotEquals("jp.cafebabe.pochi.kunai.entries.ClassNameTest", name1);
        assertEquals("jp.cafebabe.pochi.kunai.entries.ClassNameTest", name1.toString());

        assertEquals(new Name("jp.cafebabe.pochi.kunai.entries.NameTest"), name2);
        assertNotEquals("jp.cafebabe.pochi.kunai.entries.NameTest", name2);
        assertEquals("jp.cafebabe.pochi.kunai.entries.NameTest", name2.toString());

        assertNotEquals(name1, name2);
    }
}
