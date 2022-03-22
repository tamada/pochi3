package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.clpond.entities.Name;

@FunctionalInterface
public interface Relationer {
    boolean isRelate(String name1, String name2);

    default boolean isRelate(Name name1, Name name2) {
        return isRelate(name1.name(), name2.name());
    }

    default boolean isRelate(String name1, Birthmark b2) {
        return isRelate(name1, b2.name());
    }

    default boolean isRelate(String name1, Name b2) {
        return isRelate(name1, b2.name());
    }

    default boolean isRelate(Birthmark b1, Birthmark b2) {
        return isRelate(b1.name(), b2.name());
    }
}
