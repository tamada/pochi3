package jp.cafebabe.pochi.birthmarks.kgram;

import java.io.Serializable;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KGramBuilder<T extends Serializable> {
    private int kValue;

    public KGramBuilder(int kValue){
        this.kValue = kValue;
    }

    public String name() {
        return String.format("%d-gram", kValue);
    }

    public Stream<KGram<T>> build(List<T> list){
        return IntStream.rangeClosed(0, list.size() - kValue)
                .mapToObj(index -> buildKGram(list, index, kValue));
    }

    private KGram<T> buildKGram(List<T> list, int start, int length){
        return new KGram<>(list.stream()
                .skip(start).limit(length));
    }

    public static KGram<Integer> from(int... values){
        return new KGram<>(IntStream.of(values)
                .mapToObj(Integer::valueOf));
    }
}
