package jp.cafebabe.pochi.birthmarks.kgram;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record KGram<T extends Serializable>(List<T> values) implements Serializable{
    private static final long serialVersionUID = 171133658354079736L;

    public KGram(Stream<T> stream){
        this(stream.collect(Collectors.toList()));
    }

    public KGram(T[] originals){
        this(Arrays.stream(originals)
                .collect(Collectors.toList()));
    }

    public T indexOf(int index){
        return values.get(index);
    }

    public int size(){
        return values.size();
    }

    @Override
    public String toString(){
        return values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
