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
        name1 = new ClassName("jp.cafebabe.pochi.clpond.entities.ClassNameTest");
        name2 = new ClassName("jp.cafebabe/pochi/clpond/entities/NameTest");
    }

    @Test
    public void testBasic(){
        assertNotEquals(new Name("jp.cafebabe.pochi.clpond.entities.ClassNameTest"), name1);
        assertNotEquals("jp.cafebabe.pochi.clpond.entities.ClassNameTest", name1);
        assertEquals(new ClassName("jp.cafebabe.pochi.clpond.entities.ClassNameTest"), name1);
        assertEquals("jp.cafebabe.pochi.clpond.entities.ClassNameTest", name1.fqdnName());
        assertEquals("ClassNameTest", name1.name());

        assertNotEquals(new Name("jp.cafebabe.pochi.clpond.entities.NameTest"), name2);
        assertNotEquals("jp.cafebabe.pochi.clpond.entities.NameTest", name2);
        assertEquals(new ClassName("jp.cafebabe.pochi.clpond.entities.NameTest"), name2);
        assertEquals("jp.cafebabe.pochi.clpond.entities.NameTest", name2.fqdnName());
        assertEquals("NameTest", name2.name());

        assertNotEquals(name1, name2);
    }
}
