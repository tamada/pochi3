package jp.cafebabe.pochi.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Table<T> {
    private final List<T> list;
    private final Size max;

    public Table(int width, int height) {
        list = new ArrayList<T>(width * height);
        IntStream.range(0, width * height).forEach(i -> list.add(null));
        this.max = Size.of(width, height);
    }

    public T cost() {
        return list.get(list.size() - 1);
    }

    public void set(T value, Index2D index) {
        list.set(index.compute(max), value);
    }

    public T get(Index2D index) {
        return list.get(index.compute(max));
    }

    public T get(int x, int y) {
        return get(new Index2D(x, y));
    }

    public T getLatestItem() {
        return get(max.width() - 1, max.height() - 1);
    }

    public Stream<Index2D> indexStream() {
        return max.stream();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        max.stream()
                .peek(index -> sb.append(index.y() == 0? System.getProperty("line.separator"): ""))
                .map(index -> String.format("%2d ", get(index)))
                .forEach(sb::append);
        return new String(sb);
    }
}
