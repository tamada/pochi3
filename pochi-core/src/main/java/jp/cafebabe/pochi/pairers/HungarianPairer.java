package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.utils.Namer;
import jp.cafebabe.birthmarks.utils.Streamable;

import java.util.stream.Stream;

public class HungarianPairer<T extends Namer> extends AbstractPairer<T> {
    public static final PairerType TYPE = new PairerType("hungarian");

    public static final class WithSamePairBuilder<T extends Namer> implements PairerBuilder<T> {
        @Override
        public PairerType type() {
            return TYPE;
        }

        @Override
        public Pairer<T> build(Configuration config) {
            return new HungarianPairer(config);
        }

        @Override
        public Pairer<T> build(Configuration config, Relationer relationer) {
            return build(config);
        }

        @Override
        public String description() {
            return "";
        }
    }

    public HungarianPairer(Configuration config) {
        super(config);
    }

    public long count(Streamable<T> target1, Streamable<T> target2) {
        return Math.min(target1.size(), target2.size());
    }

    public long count(Streamable<T> target) {
        return target.size();
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target) {
        return target.stream()
                .map(item1 -> Pair.of(item1, item1));
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> target1, Streamable<T> target2) {
        var list1 = target1.stream().toList();
        var list2 = target2.stream().toList();
        return Stream.empty();
    }
}
