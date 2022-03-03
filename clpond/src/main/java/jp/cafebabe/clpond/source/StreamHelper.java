package jp.cafebabe.clpond.source;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class StreamHelper {
    public static <T> Stream<T> stream(Iterable<T> iterable){
        Spliterator<T> spliterator = iterable.spliterator();
        return StreamSupport.stream(spliterator, false);
    }
}
