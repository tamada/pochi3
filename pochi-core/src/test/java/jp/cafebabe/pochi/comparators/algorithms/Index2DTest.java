package jp.cafebabe.pochi.comparators.algorithms;


import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Index2DTest {
    @Test
    public void testBasic() {
        Index2D index = new Index2D(2, 3);

        assertEquals(2, index.x());
        assertEquals(3, index.y());
        assertEquals(11, index.compute(Size.of(3, 5)));
    }

    @Test
    public void testRelativePoint() {
        Index2D index2D = new Index2D(2, 3);

        Optional<Index2D> relative1 = index2D.relativeOf(-2, 1);
        assertTrue(relative1.isPresent());
        assertEquals(new Index2D(0, 4), relative1.get());

        Optional<Index2D> relative2 = index2D.relativeOf(2, -4);
        assertFalse(relative2.isPresent());

        Optional<Index2D> relative3 = index2D.relativeOf(-5, 1);
        assertFalse(relative3.isPresent());
    }

    @Test
    public void testEquals() {
        Index2D index2D = new Index2D(2, 3);

        assertFalse(Objects.equals(index2D, new Object()));
        assertFalse(Objects.equals(index2D, new Index2D(3, 2)));
        assertFalse(Objects.equals(index2D, new Index2D(2, 4)));
        assertTrue(Objects.equals(index2D, new Index2D(2, 3)));
    }
}
