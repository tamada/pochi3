package jp.cafebabe.pochi.birthmarks.kgram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KGramTest {
    private KGram<Integer> integerKgram;
    private KGram<String> stringKgram;

    @BeforeEach
    public void buildKGram(){
        integerKgram = new KGram<>(IntStream.of(1, 2, 3)
                .mapToObj(Integer::valueOf)
                .toArray(Integer[]::new));
        stringKgram = new KGram<>(Stream.of("a", "ab", "abc", "abcd")
                .toArray(String[]::new));
    }

    @Test
    public void testSizeOfKGram(){
        assertEquals(3, integerKgram.size());
        assertEquals(4, stringKgram.size());
    }

    @Test
    public void testToString(){
        assertEquals("1,2,3", integerKgram.toString());
        assertEquals("a,ab,abc,abcd", stringKgram.toString());
    }

    @Test
    public void testIndex(){
        assertEquals(1, integerKgram.indexOf(0));
        assertEquals(2, integerKgram.indexOf(1));
        assertEquals(3, integerKgram.indexOf(2));

        assertEquals("a", stringKgram.indexOf(0));
        assertEquals("ab", stringKgram.indexOf(1));
        assertEquals("abc", stringKgram.indexOf(2));
        assertEquals("abcd", stringKgram.indexOf(3));
    }
}
