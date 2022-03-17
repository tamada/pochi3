package jp.cafebabe.birthmarks.extractors.elements;

import jp.cafebabe.birthmarks.extractors.elements.DoubleElement;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleElementTest {
    @Nested
    public class ValueTest {
        private DoubleElement element = new DoubleElement(10.3);
        @Test
        public void testValue() {
            assertThrows(IllegalStateException.class, () -> element.intValue());
            assertEquals(10.3, element.doubleValue());
            assertEquals("10.3", element.value());

            assertTrue(element.compareTo(new DoubleElement(5)) > 0);
        }
    }
}
