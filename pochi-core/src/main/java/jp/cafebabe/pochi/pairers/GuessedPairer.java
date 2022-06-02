package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.pochi.pairers.relationers.FullyMatchRelationer;
import jp.cafebabe.pochi.pairers.relationers.SimpleClassNameRelationer;

import java.util.Optional;
import java.util.stream.Stream;

public class GuessedPairer<T extends Namer> extends AbstractPairer<T> {
    public static final PairerType TYPE_SIMPLE = new PairerType("guessed-simple");
    public static final PairerType TYPE_FULLY = new PairerType("guessed-fully");

    private final Relationer relationer;

    private GuessedPairer(Configuration config, Relationer relationer) {
        super(config);
        this.relationer = relationer;
    }

    public long count(Streamable<T> target1, Streamable<T> target2) {
        return pair(target1, target2).count();
    }

    public long count(Streamable<T> target) {
        return pair(target).count();
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target) {
        return pair(target, target);
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target1, Streamable<T> target2) {
        return target1.stream()
                .map(item1 -> createPair(item1, target2))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Optional<Pair<T, T>> createPair(T item1, Streamable<T> target) {
        return target.stream().filter(item2 -> relationer.isRelate(item1, item2))
                .map(item2 -> new Pair<>(item1, item2))
                .findFirst();
    }

    public static final class SimpleBuilder<T extends Namer> extends Builder<T> {
        public SimpleBuilder() {
            super(TYPE_SIMPLE, new SimpleClassNameRelationer());
        }
    }

    public static final class FullyBuilder<T extends Namer> extends Builder<T> {
        public FullyBuilder() {
            super(TYPE_FULLY, new FullyMatchRelationer());
        }
    }

    public static abstract class Builder<T extends Namer> implements PairerBuilder<T> {
        private final Relationer relationer;
        private final PairerType type;

        public Builder(PairerType type, Relationer relationer) {
            this.type = type;
            this.relationer = relationer;
        }

        @Override
        public PairerType type() {
            return type;
        }

        @Override
        public Pairer<T> build(Configuration config) {
            return new GuessedPairer<>(config, relationer);
        }

        @Override
        public String description() {
            return String.format("pairs by the names with %s", relationer.getClass().getSimpleName());
        }
    }
}
