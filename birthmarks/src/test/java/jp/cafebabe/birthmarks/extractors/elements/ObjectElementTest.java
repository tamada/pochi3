package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.elements.ObjectElement;
import jp.cafebabe.birthmarks.io.Marshaller;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectElementTest {
    @Test
    public void testConstructor() {
        assertThrows(NullPointerException.class, () -> new ObjectElement<>(null));
        assertThrows(NullPointerException.class, () -> new ObjectElement<>(null, null));
        assertThrows(NullPointerException.class, () -> new ObjectElement<>("raw", null));
    }

    @Nested
    public class BasicTest {
        private ObjectElement<String> element = new ObjectElement<>("value");

        @Test
        public void testEquals() {
            assertNotEquals("value", element);
            assertNotEquals(null, element);
            assertNotEquals(new ObjectElement<>("not_equals_value"), element);
            assertEquals(new ObjectElement<>("value"), element);
        }

        @Test
        public void testMarshal() {
            StringWriter out = new StringWriter();
            element.marshal(Marshaller.of(out));
            assertEquals("\"value\"", out.toString());
        }

        @Test
        public void testIllegalMethod() {
            assertThrows(IllegalStateException.class, () -> element.intValue());
            assertThrows(IllegalStateException.class, () -> element.doubleValue());
            assertEquals("value", element.value());
        }

        @Test
        public void testGetter() {
            assertEquals("value", element.rawValue());
            assertNotNull(element.stringer());
        }
    }
}
