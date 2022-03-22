package jp.cafebabe.birthmarks.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtil {
    public static Stream<JsonElement> stream(JsonArray array) {
        return toStream(array.iterator());
    }

    public static <E> Stream<E> toStream(Iterator<E> iterator) {
        var spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }
}
