package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.elements.PairElement;

import java.util.Comparator;

/**
 * <code>
 * flowchart TD
 *   list --> set
 *   frequency --> set
 *   list --> frequency
 *   g[graph] --> set
 *   g --> frequency
 * </code>
 */
public class Converter {
    public static ListBirthmark toList(Birthmark birthmark, Comparator<Element> comparator) {
        return new ListBirthmark(birthmark.metadata(), birthmark.stream().sorted(comparator));
    }

    public static ListBirthmark toList(Birthmark birthmark) {
        if(birthmark instanceof ListBirthmark lb)
            return lb;
        return new ListBirthmark(birthmark.metadata(), birthmark.stream());
    }

    public static VectorBirthmark toFrequency(Birthmark birthmark) {
        if(birthmark instanceof VectorBirthmark vb)
            return vb;
        return new VectorBirthmark(birthmark.metadata(), birthmark.stream());
    }

    public static SetBirthmark toSet(Birthmark birthmark) {
        return switch(birthmark.containerType()) {
            case List -> toSet((ListBirthmark) birthmark);
            case Set -> (SetBirthmark) birthmark;
            case Graph -> toSet((GraphBirthmark) birthmark);
            case Vector -> toSet((VectorBirthmark) birthmark);
        };
    }

    public static SetBirthmark toSet(GraphBirthmark birthmark) {
        return new SetBirthmark(birthmark.metadata(), birthmark.stream());
    }

    public static SetBirthmark toSet(ListBirthmark birthmark) {
        return new SetBirthmark(birthmark.metadata(), birthmark.stream());
    }

    public static SetBirthmark toSet(VectorBirthmark birthmark) {
        return new SetBirthmark(birthmark.metadata(),
                birthmark.stream().map(e -> (PairElement)e)
                        .map(pe -> pe.element()));
    }
}
