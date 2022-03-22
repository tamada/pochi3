package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.elements.PairElement;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    private URI here = URI.create(".");

    @Nested
    public class ToListTest {
        @Test
        public void testListToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .list(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(10, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).toArray(String[]::new);
            assertArrayEquals(new String[] { "e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4" },
                    values);
        }

        @Test
        public void testSetToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .set(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(4, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).sorted().toArray(String[]::new);
            assertArrayEquals(new String[] { "e1", "e2", "e3", "e4" },
                    values);
        }

        @Test
        public void testVectorToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .vector(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(4, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).sorted().toArray(String[]::new);
            assertArrayEquals(new String[] { "e1", "e2", "e3", "e4" },
                    values);
        }
    }

    @Nested
    public class ToListTestWithComparator {
        Comparator<Element> comparator = (e1, e2) -> e2.value().compareTo(e1.value());
        @Test
        public void testListToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .list(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from, comparator);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(10, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).toArray(String[]::new);
            assertArrayEquals(new String[] { "e4", "e4", "e4", "e4", "e3", "e3", "e3", "e2", "e2", "e1" },
                    values);
        }

        @Test
        public void testSetToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .set(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from, comparator);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(4, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).toArray(String[]::new);
            assertArrayEquals(new String[] { "e4", "e3", "e2", "e1" },
                    values);
        }

        @Test
        public void testVectorToList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .vector(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toList(from, comparator);
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(4, birthmark.size());
            String[] values = birthmark.stream().map(e -> e.value()).toArray(String[]::new);
            assertArrayEquals(new String[] { "e4", "e3", "e2", "e1" },
                    values);
        }
    }

    @Nested
    public class ToSetTest {
        @Test
        public void testListToSet() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .list(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toSet(from);
            assertEquals(ContainerType.Set, birthmark.containerType());
            assertEquals(4, birthmark.size());

            Element[] elements = birthmark.stream().sorted().toArray(Element[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());
        }

        @Test
        public void testSetToSet() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .set(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toSet(from);
            assertEquals(ContainerType.Set, birthmark.containerType());
            assertEquals(4, birthmark.size());

            Element[] elements = birthmark.stream().sorted().toArray(Element[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());
        }

        @Test
        public void testVectorToSet() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .vector(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toSet(from);
            assertEquals(ContainerType.Set, birthmark.containerType());
            assertEquals(4, birthmark.size());

            Element[] elements = birthmark.stream().sorted().toArray(Element[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());
        }
    }

    @Nested
    public class ToVectorTest {
        Comparator<Element> comparator = (e1, e2) -> e1.value().compareTo(e2.value());

        @Test
        public void testFromList() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .list(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toFrequency(from);
            assertEquals(ContainerType.Vector, birthmark.containerType());
            assertEquals(4, birthmark.size());

            PairElement[] elements = birthmark.stream().sorted(comparator).toArray(PairElement[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());

            assertEquals(1, elements[0].count());
            assertEquals(2, elements[1].count());
            assertEquals(3, elements[2].count());
            assertEquals(4, elements[3].count());
        }

        @Test
        public void testFromSet() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .set(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toFrequency(from);
            assertEquals(ContainerType.Vector, birthmark.containerType());
            assertEquals(4, birthmark.size());

            PairElement[] elements = birthmark.stream().sorted(comparator).toArray(PairElement[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());

            assertEquals(1, elements[0].count());
            assertEquals(1, elements[1].count());
            assertEquals(1, elements[2].count());
            assertEquals(1, elements[3].count());
        }

        @Test
        public void testFromVector() {
            Birthmark from = new Builder().metadata("name1", here, "type1")
                    .vector(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            Birthmark birthmark = Converter.toFrequency(from);
            assertEquals(ContainerType.Vector, birthmark.containerType());
            assertEquals(4, birthmark.size());

            PairElement[] elements = birthmark.stream().sorted(comparator).toArray(PairElement[]::new);
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());

            assertEquals(1, elements[0].count());
            assertEquals(2, elements[1].count());
            assertEquals(3, elements[2].count());
            assertEquals(4, elements[3].count());
        }
    }
}
