package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.*;
import jp.cafebabe.birthmarks.entities.elements.LongElement;
import jp.cafebabe.birthmarks.entities.elements.StringElement;
import jp.cafebabe.clpond.entities.Name;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BirthmarkParserTest {
    @Nested
    public class ExceptionTest {
        @Test
        public void testUnknownContainerType() throws Exception {
            String json = """
                    [{"container":"unknown","metadata":{"name":"name1","location":".","type":"type1"},"elements":[2,3,5,7,11,13,17,19]}]
                    """.trim();
            BirthmarkParser parser = new BirthmarkParser();
            Birthmarks birthmarks = parser.parse(json);
            assertTrue(birthmarks.hasFailure());
            assertEquals(1, birthmarks.failures().count());
        }

        @Test
        public void testInvalidElementFormat() throws Exception {
            String json = """
                    [{"container":"vector","metadata":{"name":"name1","location":".","type":"type1"},"elements":[2]}]
                    """.trim();
            BirthmarkParser parser = new BirthmarkParser();
            Birthmarks birthmarks = parser.parse(json);
            assertTrue(birthmarks.hasFailure());
            assertEquals(1, birthmarks.failures().count());
        }
    }

    @Test
    public void testParseListBirthmark() throws Exception {
        String json = """
            [{"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":[2,3,5,7,11,13,17,19]}]
            """.trim();

        BirthmarkParser parser = new BirthmarkParser();
        Birthmarks birthmarks = parser.parse(json);
        assertEquals(1, birthmarks.size());

        Birthmark birthmark = birthmarks.stream().findFirst().get();
        assertEquals(Name.of("name1"), birthmark.name());
        assertEquals(URI.create("."), birthmark.location());
        assertEquals(new BirthmarkType("type1"), birthmark.type());
        assertEquals(ContainerType.List, birthmark.containerType());
        assertEquals(8, birthmark.size());

        List<Element> list = birthmark.stream().collect(Collectors.toList());
        assertEquals(new LongElement( 2), list.get(0));
        assertEquals(new LongElement( 3), list.get(1));
        assertEquals(new LongElement( 5), list.get(2));
        assertEquals(new LongElement( 7), list.get(3));
        assertEquals(new LongElement(11), list.get(4));
        assertEquals(new LongElement(13), list.get(5));
        assertEquals(new LongElement(17), list.get(6));
        assertEquals(new LongElement(19), list.get(7));
    }

    @Test
    public void testParseVectorBirthmark() throws Exception {
        String json = """
            [{"container":"vector","metadata":{"name":"name1","location":".","type":"type1"},"elements":["e1=1","e2=2"]}]
            """.trim();

        BirthmarkParser parser = new BirthmarkParser();
        Birthmarks birthmarks = parser.parse(json);
        assertEquals(1, birthmarks.size());

        Birthmark birthmark = birthmarks.stream().findFirst().get();
        assertEquals(Name.of("name1"), birthmark.name());
        assertEquals(URI.create("."), birthmark.location());
        assertEquals(new BirthmarkType("type1"), birthmark.type());
        assertEquals(ContainerType.Vector, birthmark.containerType());
        assertEquals(2, birthmark.size());

        List<Element> list = birthmark.stream().sorted((e1, e2) -> e1.value().compareTo(e2.value())).collect(Collectors.toList());
        assertEquals("e1", list.get(0).value());
        assertEquals("e2", list.get(1).value());
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
    }

    @Test
    public void testParseSetBirthmark() throws Exception {
        String json = """
            [{"container":"set","metadata":{"name":"name1","location":".","type":"type1"},"elements":["a","b","c","a"]}]
            """.trim();

        BirthmarkParser parser = new BirthmarkParser();
        Birthmarks birthmarks = parser.parse(json);
        assertEquals(1, birthmarks.size());

        Birthmark birthmark = birthmarks.stream().findFirst().get();
        assertEquals(Name.of("name1"), birthmark.name());
        assertEquals(URI.create("."), birthmark.location());
        assertEquals(new BirthmarkType("type1"), birthmark.type());
        assertEquals(ContainerType.Set, birthmark.containerType());
        assertEquals(3, birthmark.size());

        List<Element> list = birthmark.stream().sorted().collect(Collectors.toList());
        assertEquals(new StringElement("a"), list.get(0));
        assertEquals(new StringElement("b"), list.get(1));
        assertEquals(new StringElement("c"), list.get(2));
    }
}
