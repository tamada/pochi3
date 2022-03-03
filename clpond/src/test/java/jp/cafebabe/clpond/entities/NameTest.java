package jp.cafebabe.clpond.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class NameTest {
    private Name name;

    @BeforeEach
    public void setUp(){
        name = new Name("Haruaki Tamada");
    }

    @Test
    public void testBasic(){
        assertThat(name, is(new Name("Haruaki Tamada")));
        assertThat(name, is(not(new Name("Nanashi no Gonbe"))));
        assertThat(name, is(not("Haruaki Tamada")));
        assertThat(name, is(not(new Object())));

        assertThat(name.toString(), is("Haruaki Tamada"));
        assertThat(name.name(), is("Haruaki Tamada"));
    }

    @Test
    public void testEquals() {
        assertThat(name.equals(new Name("Haruaki Tamada")), is(true));
        assertThat(name.equals(new Name("Different Name")), is(false));
        assertThat(name.equals(new Object()), is(false));
        assertThat(name.equals(null), is(false));
    }
}
