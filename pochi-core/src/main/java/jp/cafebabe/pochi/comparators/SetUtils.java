package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.utils.Streamable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtils {
    public static <E> Set<E> union(Streamable<E> left, Streamable<E> right) {
        Set<E> union = new HashSet<>();
        left.stream().forEach(union::add);
        right.stream().forEach(union::add);
        return Collections.unmodifiableSet(union);
    }

    public static <E> Set<E> intersect(Streamable<E> left, Streamable<E> right) {
        Set<E> rightSet = right.stream().collect(Collectors.toSet());
        return left.stream()
                .filter(rightSet::contains)
                .collect(Collectors.toSet());
    }

    public static <E> List<E> list(Streamable<E> streamable) {
        return streamable.stream()
                .collect(Collectors.toList());
    }
}
