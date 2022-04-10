package jp.cafebabe.birthmarks.entities.elements;

import jp.cafebabe.birthmarks.entities.Element;

import java.util.function.Function;

public class ElementBuilder {
    public static Element build(Number number) {
        return switch(number.getClass().getName()) {
            case "java.lang.Short", "java.lang.Byte", "java.lang.Long", "java.lang.Integer"
                    -> build(number.longValue());
            case "java.lang.Double", "java.lang.Float"
                    -> build(number.doubleValue());
            default -> buildNumberElement(number);
        };
    }

    private static Element buildNumberElement(Number number) {
        String value = number.toString();
        if(value.contains("."))
            return build(number.doubleValue());
        return build(number.longValue());
    }

    public static Element build(long value) {
        return new LongElement(value);
    }

    public static Element build(String value) {
        return new StringElement(value);
    }

    public static Element build(double value) {
        return new DoubleElement(value);
    }

    public static Element build(String value, long count) {
        return new PairElement(value, count);
    }

    public static <E> Element build(E value) {
        return build(value, Object::toString);
    }

    public static <E> Element build(E value, Function<E, String> stringer) {
        if(value instanceof Element e) {
            return e;
        } else if(value instanceof Number number) {
            return build(number);
        } else if(value instanceof String string) {
            return build(string);
        }
        return new ObjectElement<>(value, stringer);
    }
}
