package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.utils.Streamable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetUtilsTest {
    @Test
    public void testUnion() {
        var list1 = List.of("a", "b", "c", "d", "e");
        var list2 = List.of("b", "d", "f");
        var list3 = SetUtils.union(toStreamable(list1), toStreamable(list2));
        assertEquals(6, list3.size());
        assertArrayEquals(new String[] { "a", "b", "c", "d", "e", "f" }, list3.toArray(String[]::new));
    }

    @Test
    public void testIntersect() {
        var list1 = List.of("a", "b", "c", "d", "e");
        var list2 = List.of("b", "d", "f");
        var list3 = SetUtils.intersect(toStreamable(list1), toStreamable(list2));
        assertEquals(2, list3.size());
        assertArrayEquals(new String[] { "b", "d", }, list3.toArray(String[]::new));
    }

    public Streamable<String> toStreamable(List<String> list) {
        return () -> list.stream();
    }
}
