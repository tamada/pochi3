package jp.cafebabe.pochi.birthmarks.kgram;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KGramBuilderTest {
    @Test
    public void test2GramBuilder(){
        KGramBuilder<Integer> builder = new KGramBuilder<>(2);

        List<KGram<Integer>> list = builder.build(IntStream
                .of(1, 2, 3, 4, 5, 6, 7)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        assertEquals(6, list.size());
        assertEquals(2, list.get(0).size());
        assertEquals(1, list.get(0).indexOf(0));
        assertEquals(2, list.get(0).indexOf(1));
        assertEquals(2, list.get(1).indexOf(0));
        assertEquals(3, list.get(1).indexOf(1));
        assertEquals(3, list.get(2).indexOf(0));
        assertEquals(4, list.get(2).indexOf(1));
        assertEquals(4, list.get(3).indexOf(0));
        assertEquals(5, list.get(3).indexOf(1));
        assertEquals(5, list.get(4).indexOf(0));
        assertEquals(6, list.get(4).indexOf(1));
        assertEquals(6, list.get(5).indexOf(0));
        assertEquals(7, list.get(5).indexOf(1));
    }

    @Test
    public void test4GramBuilder(){
        KGramBuilder<Integer> builder = new KGramBuilder<>(4);

        List<KGram<Integer>> list = builder.build(IntStream
                .of(1, 2, 3, 4, 5, 6, 7)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        assertEquals(4, list.size());
        assertEquals(4, list.get(0).size());
        assertEquals(1, list.get(0).indexOf(0));
        assertEquals(2, list.get(0).indexOf(1));
        assertEquals(3, list.get(0).indexOf(2));
        assertEquals(4, list.get(0).indexOf(3));

        assertEquals(2, list.get(1).indexOf(0));
        assertEquals(3, list.get(1).indexOf(1));
        assertEquals(4, list.get(1).indexOf(2));
        assertEquals(5, list.get(1).indexOf(3));

        assertEquals(3, list.get(2).indexOf(0));
        assertEquals(4, list.get(2).indexOf(1));
        assertEquals(5, list.get(2).indexOf(2));
        assertEquals(6, list.get(2).indexOf(3));

        assertEquals(4, list.get(3).indexOf(0));
        assertEquals(5, list.get(3).indexOf(1));
        assertEquals(6, list.get(3).indexOf(2));
        assertEquals(7, list.get(3).indexOf(3));
    }
}
