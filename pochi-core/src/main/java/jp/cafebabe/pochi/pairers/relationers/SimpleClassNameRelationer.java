package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.pairers.RelationerType;

import java.util.Objects;

public class SimpleClassNameRelationer implements Relationer {
    @Override
    public boolean isRelate(String name1, String name2) {
        return Objects.equals(extractClassName(name1), extractClassName(name2));
    }

    private String extractClassName(String name) {
         int index = name.lastIndexOf('.');
         if(index >= 0)
             return name.substring(index + 1);
         return name;
    }

    public static final class Builder implements TaskBuilder<Relationer, RelationerType> {
        @Override
        public RelationerType type() {
            return RelationerType.of("classname");
        }

        @Override
        public String description() {
            return "matching by class name (not fully qualified name)";
        }

        @Override
        public Relationer build(Configuration config) {
            return new SimpleClassNameRelationer();
        }
    }
}
