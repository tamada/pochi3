package jp.cafebabe.clpond.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    private Name name;

    @BeforeEach
    public void setUp(){
        name = new Name("Haruaki Tamada");
    }

    @Test
    public void testBasic(){
        assertEquals(new Name("Haruaki Tamada"), name);
        assertNotEquals(new Name("Nanashi no Gonbe"), name);
        assertNotEquals("Haruaki Tamada", name);
        assertNotEquals(new Object(), name);

        assertEquals("Haruaki Tamada", name.name());
    }

    @Test
    public void testEquals() {
        assertTrue(name.equals(new Name("Haruaki Tamada")));
        assertFalse(name.equals(new Name("Different Name")));
        assertFalse(name.equals(new Object()));
        assertFalse(name.equals(null));
    }
}
