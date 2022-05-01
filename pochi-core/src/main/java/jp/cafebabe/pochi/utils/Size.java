package jp.cafebabe.pochi.utils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

record Size(int width, int height) {
    public Stream<Index2D> stream() {
        return IntStream.range(0, width())
                .boxed()
                .flatMap(this::yStream);
    }

    private Stream<Index2D> yStream(int x) {
        return IntStream.range(0, height())
                .mapToObj(y -> new Index2D(x, y));
    }

    public static Size of(int maxWidth, int maxHeight) {
        return new Size(maxWidth, maxHeight);
    }
}
