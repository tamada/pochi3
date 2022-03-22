package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.elements.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {
    private URI here = URI.create(".");

    @Nested
    public class BuildTest {
        @Test
        public void testBuiltBirthmark() {
            Birthmark birthmark = new Builder().type("type1")
                    .name("name1").location(here)
                    .list(Stream.of("e1", "e2"));
            assertEquals(ContainerType.List, birthmark.containerType());
            assertEquals(new BirthmarkType("type1"), birthmark.type());
            assertEquals(here, birthmark.location());
            assertEquals(2, birthmark.size());
            assertArrayEquals(new StringElement[] {
                    new StringElement("e1"),
                    new StringElement("e2"),
            }, birthmark.stream().toArray());
        }

        @Test
        public void testBuiltElements() {
            Instant now = Instant.now();
            Birthmark birthmark = new Builder().type("type1")
                    .name("name1").location(here)
                    .list(Stream.of("e1", 2, 3.0, now), e -> ElementBuilder.build(e));
            assertEquals(4, birthmark.size());
            assertArrayEquals(new Element[] {
                    new StringElement("e1"),
                    new LongElement(2),
                    new DoubleElement(3.0),
                    new ObjectElement<>(now),
            }, birthmark.stream().toArray());
        }

        @Test
        public void testSetBirthmark() {
            Birthmark birthmark = new Builder().metadata("name1", here, "type1")
                    .set(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            assertEquals(4, birthmark.size());
            assertEquals(ContainerType.Set, birthmark.containerType());
            assertArrayEquals(Arrays.stream(new String[] {"e1", "e2", "e3", "e4"})
                            .map(ElementBuilder::build).toArray(),
                    birthmark.stream().sorted().toArray());
        }

        @Test
        public void testVectorBirthmark() {
            Birthmark birthmark = new Builder().metadata("name1", here, "type1")
                    .vector(Stream.of("e1", "e4", "e2", "e3", "e2", "e4", "e4", "e3", "e3", "e4"));
            assertEquals(ContainerType.Vector, birthmark.containerType());
            assertEquals(4, birthmark.size());
            PairElement[] elements = birthmark.stream().sorted((e1, e2) -> e1.value().compareTo(e2.value())).toArray(size -> new PairElement[size]);
            assertEquals(1, elements[0].count());
            assertEquals(2, elements[1].count());
            assertEquals(3, elements[2].count());
            assertEquals(4, elements[3].count());
            assertEquals("e1", elements[0].value());
            assertEquals("e2", elements[1].value());
            assertEquals("e3", elements[2].value());
            assertEquals("e4", elements[3].value());
        }
    }

    @Nested
    public class ValidateTest {
        @Test
        public void testAllMetadataAreNull() {
            assertThrows(IllegalStateException.class, () -> new Builder().list(Stream.of("e1")));
        }

        @Test
        public void testNameIsNull() {
            assertThrows(IllegalStateException.class, () -> new Builder()
                    .type("type1")
                    .location(here).list(Stream.of("e1")));
        }

        @Test
        public void testTypeIsNull() {
            assertThrows(IllegalStateException.class, () -> new Builder()
                    .name("name1")
                    .location(here).list(Stream.of("e1")));
        }

        @Test
        public void testLocationIsNull() {
            assertThrows(IllegalStateException.class, () -> new Builder()
                    .type("type1")
                    .name("name1").list(Stream.of("e1")));
        }
    }
}
