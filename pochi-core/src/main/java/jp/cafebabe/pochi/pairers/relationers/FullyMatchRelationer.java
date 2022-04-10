package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.pairers.RelationerType;

import java.util.Objects;

public class FullyMatchRelationer implements Relationer {
    @Override
    public boolean isRelate(String name1, String name2) {
        return Objects.equals(name1, name2);
    }

    public static final class Builder implements TaskBuilder<Relationer, RelationerType> {

        @Override
        public RelationerType type() {
            return RelationerType.of("fully");
        }

        @Override
        public String description() {
            return "matching by fully qualified name";
        }

        @Override
        public Relationer build(Configuration config) {
            return new FullyMatchRelationer();
        }
    }
}
