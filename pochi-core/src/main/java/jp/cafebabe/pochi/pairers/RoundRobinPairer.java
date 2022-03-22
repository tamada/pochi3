package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;

import java.util.stream.Stream;

public class RoundRobinPairer extends AbstractPairer {
    public static final PairerType TYPE = new PairerType("RoundRobin");
    public static final PairerType SAME_PAIR_TYPE = new PairerType("RoundRobinWithSamePair");

    private boolean includeSamePair = false;

    public static final class Builder implements PairerBuilder {
        @Override
        public PairerType type() {
            return TYPE;
        }

        @Override
        public Pairer build(Configuration config) {
            return new RoundRobinPairer(false, config);
        }
    }

    public static final class WithSamePairBuilder implements PairerBuilder {
        @Override
        public PairerType type() {
            return SAME_PAIR_TYPE;
        }

        @Override
        public Pairer build(Configuration config) {
            return new RoundRobinPairer(true, config);
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

    public long count(Birthmarks target1, Birthmarks target2) {
        return target1.size() * target2.size();
    }

    public long count(Birthmarks target) {
        long size = target.size();
        size = includeSamePair? size: size - 1;
        return (size + 1) * size / 2;
    }

    @Override
    public Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks target) {
        var value = firstIndex();
        return target.stream()
                .flatMap(item1 -> matchPair(item1, value, target));
    }

    @Override
    public Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks target1, Birthmarks target2) {
        return target1.stream()
                .flatMap(left -> target2.stream()
                        .map(right -> new Pair<>(left, right)));
    }

    private Stream<Pair<Birthmark, Birthmark>> matchPair(Birthmark baseTarget, int index, Birthmarks list){
        return list.stream()
                .skip(index)
                .map(target -> new Pair<>(baseTarget, target));
    }
}
