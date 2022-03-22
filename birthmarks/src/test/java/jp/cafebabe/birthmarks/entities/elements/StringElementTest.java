package jp.cafebabe.birthmarks.entities.elements;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringElementTest {
    @Test
    public void testConstructor() {
        assertThrows(NullPointerException.class, () -> new StringElement(null));
    }

    @Nested
    public class ThrowsTest {
        private StringElement element = new StringElement("value");
        @Test
        public void testIllegalMethod() {
            assertThrows(IllegalStateException.class, () -> element.intValue());
            assertThrows(IllegalStateException.class, () -> element.doubleValue());
        }
    }
}
