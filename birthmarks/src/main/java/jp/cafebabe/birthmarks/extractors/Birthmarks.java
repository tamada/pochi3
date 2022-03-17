package jp.cafebabe.birthmarks.extractors;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Birthmarks implements Serializable {
    private List<Birthmark> list;

    private Birthmarks(Stream<Birthmark> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public int size() {
        return list.size();
    }

    public Stream<Birthmark> stream() {
        return list.stream();
    }

    public static Birthmarks of(Stream<Birthmark> stream) {
        return new Birthmarks(stream);
    }
}
