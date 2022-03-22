package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.pochi.pairers.relationers.RelationerBuilder;

import java.util.Optional;
import java.util.stream.Stream;

public class GuessedPairer<T extends Namer> extends AbstractPairer<T> {
    public static final PairerType TYPE = new PairerType("Guessed");

    private Relationer relationer;

    private GuessedPairer(Configuration config) {
        super(config);
        this.relationer = RelationerBuilder.build(config);
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
                .filter(optional -> optional.isPresent())
                .map(pair -> pair.get());
    }

    private Optional<Pair<T, T>> createPair(T item1, Streamable<T> target) {
        return target.stream().filter(item2 -> relationer.isRelate(item1, item2))
                .map(item2 -> new Pair<>(item1, item2))
                .findFirst();
    }

    public static final class Builder implements PairerBuilder {
        @Override
        public PairerType type() {
            return TYPE;
        }

        @Override
        public Pairer build(Configuration config) {
            return new GuessedPairer(config);
        }
    }
}
