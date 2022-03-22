package jp.cafebabe.pochi.comparators.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeTest {
    @Test
    public void testBasic() {
        Index2D[] indexes = Size.of(3, 4).stream().toArray(Index2D[]::new);

        assertEquals(12, indexes.length);
        assertEquals(new Index2D(0, 0), indexes[ 0]);
        assertEquals(new Index2D(0, 1), indexes[ 1]);
        assertEquals(new Index2D(0, 2), indexes[ 2]);
        assertEquals(new Index2D(0, 3), indexes[ 3]);
        assertEquals(new Index2D(1, 0), indexes[ 4]);
        assertEquals(new Index2D(1, 1), indexes[ 5]);
        assertEquals(new Index2D(1, 2), indexes[ 6]);
        assertEquals(new Index2D(1, 3), indexes[ 7]);
        assertEquals(new Index2D(2, 0), indexes[ 8]);
        assertEquals(new Index2D(2, 1), indexes[ 9]);
        assertEquals(new Index2D(2, 2), indexes[10]);
        assertEquals(new Index2D(2, 3), indexes[11]);
    }
}
