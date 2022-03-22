package jp.cafebabe.birthmarks.entities.elements;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongElementTest {
    @Nested
    public class ValueTest {
        private LongElement element = new LongElement(10);
        @Test
        public void testValue() {
            assertEquals(10, element.intValue());
            assertEquals(10.0, element.doubleValue());
            assertEquals("10", element.value());

            assertTrue(element.compareTo(new LongElement(5)) > 0);
        }
    }
}
