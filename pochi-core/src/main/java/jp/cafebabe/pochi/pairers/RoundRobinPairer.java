package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;
import jp.cafebabe.pochi.pairers.relationers.RelationerFactory;

import java.util.stream.Stream;

public class RoundRobinPairer<T extends Namer> extends AbstractPairer<T> {
    public static final PairerType TYPE = new PairerType("round_robin");
    public static final PairerType SAME_PAIR_TYPE = new PairerType("round_robin_with_same_pair");

    private final boolean includeSamePair;

    public static final class Builder<T extends Namer> implements PairerBuilder<T> {
        @Override
        public PairerType type() {
            return TYPE;
        }

        @Override
        public Pairer<T> build(Configuration config) {
            return new RoundRobinPairer<>(false, config);
        }

        @Override
        public Pairer<T> build(Configuration config, Relationer relationer) {
            return build(config);
        }
    }

    public static final class WithSamePairBuilder<T extends Namer> implements PairerBuilder<T> {
        @Override
        public PairerType type() {
            return SAME_PAIR_TYPE;
        }

        @Override
        public Pairer<T> build(Configuration config) {
            return new RoundRobinPairer<>(true, config);
        }

        @Override
        public Pairer<T> build(Configuration config, Relationer relationer) {
            return build(config);
        }
    }

    public RoundRobinPairer(boolean includeSamePair,
                            Configuration config) {
        super(config);
        this.includeSamePair = includeSamePair;
    }

    private int firstIndex(){
        if(includeSamePair)
            return 0;
        return 1;
    }

    public long count(Streamable<T> target1, Streamable<T> target2) {
        return target1.size() * target2.size();
    }

    public long count(Streamable<T> target) {
        long size = target.size();
        size = includeSamePair? size: size - 1;
        return (size + 1) * size / 2;
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target) {
        var value = firstIndex();
        return target.stream()
                .flatMap(item1 -> matchPair(item1, value, target));
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target1, Streamable<T> target2) {
        return target1.stream()
                .flatMap(left -> target2.stream()
                        .map(right -> new Pair<>(left, right)));
    }

    private Stream<Pair<T, T>> matchPair(T baseTarget, int index, Streamable<T> list){
        return list.stream()
                .skip(index)
                .map(target -> new Pair<>(baseTarget, target));
    }
}
