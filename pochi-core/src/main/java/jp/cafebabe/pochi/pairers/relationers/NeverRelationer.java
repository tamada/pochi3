package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.pairers.RelationerType;

public class NeverRelationer implements Relationer {
    @Override
    public boolean isRelate(String name1, String name2) {
        return false;
    }

    public static class Builder implements TaskBuilder<Relationer, RelationerType> {

        @Override
        public RelationerType type() {
            return RelationerType.of("never");
        }

        @Override
        public String description() {
            return "never matching any pairs";
        }

        @Override
        public Relationer build(Configuration config) {
            return new NeverRelationer();
        }
    }
}
