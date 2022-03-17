package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.elements.PairElement;
import jp.cafebabe.birthmarks.extractors.elements.StringElement;
import jp.cafebabe.birthmarks.io.Marshaller;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairElementTest {
    @Nested
    public class BasicTest {
        private PairElement element = new PairElement("value", 10);
        @Test
        public void testValue() {
            assertEquals(10, element.intValue());
            assertEquals(10d, element.doubleValue());
            assertEquals("value", element.value());
        }

        @Test
        public void testMarshal() {
            StringWriter out = new StringWriter();
            element.marshal(Marshaller.of(out));
            assertEquals("\"value=10\"", out.toString());
        }

        @Test
        public void testGetter() {
            assertEquals(new StringElement("value"), element.element());
            assertEquals(10, element.count());
        }
    }
}
