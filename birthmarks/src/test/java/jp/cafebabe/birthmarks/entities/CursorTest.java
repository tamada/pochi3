package jp.cafebabe.birthmarks.entities;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CursorTest {
    @Nested
    public class ExceptionTest {
        @Test
        public void testNegativeMax() {
            assertThrows(IllegalArgumentException.class, () -> Cursor.of(-1));
        }
    }

    @Nested
    public class BasicTest {
        @Test
        public void testIncrement() {
            Cursor cursor = Cursor.of(2);
            assertFalse(cursor.isLast());
            assertEquals(1, cursor.increment());
            assertFalse(cursor.isLast());
            assertEquals(2, cursor.increment());
            assertTrue(cursor.isLast());
            assertThrows(IllegalStateException.class, () -> cursor.increment());
        }

        @Test
        public void testIncrementIsLast() {
            Cursor cursor = Cursor.of(2);
            assertFalse(cursor.isLast());
            assertFalse(cursor.incrementIsLast());
            assertTrue(cursor.incrementIsLast());
            assertThrows(IllegalStateException.class, () -> cursor.incrementIsLast());
        }
    }
}
