package jp.cafebabe.pochi.cli.time;

import jp.cafebabe.birthmarks.comparators.Pair;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Watch {
    private long start;
    private Duration item;

    public void start() {
        this.start = System.nanoTime();
    }

    public Duration finish() {
        item = Duration.ofNanos(System.nanoTime() - start);
        return item;
    }

    public Duration get() {
        return item;
    }

    public static Duration measure(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return Duration.ofNanos(end - start);
    }

    public static <V> Pair<V, Duration> measure(Supplier<V> supplier) {
        long start = System.nanoTime();
        V value = supplier.get();
        long end = System.nanoTime();
        return Pair.of(value, Duration.ofNanos(end - start));
    }

    public static <T> Duration measure(Consumer<T> action, T value) {
        long start = System.nanoTime();
        action.accept(value);
        long end = System.nanoTime();
        return Duration.ofNanos(end - start);
    }

    public static <V1, V2> Duration measure(BiConsumer<V1, V2> action, V1 v1, V2 v2) {
        long start = System.nanoTime();
        action.accept(v1, v2);
        long end = System.nanoTime();
        return Duration.ofNanos(end - start);
    }

    public static <F, T> Pair<T, Duration> measure(Function<F, T> mapper, F value) {
        long start = System.nanoTime();
        T result = mapper.apply(value);
        long end = System.nanoTime();
        return Pair.of(result, Duration.ofNanos(end - start));
    }
}
