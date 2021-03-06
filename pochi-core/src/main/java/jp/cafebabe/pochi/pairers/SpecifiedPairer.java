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

import java.util.List;
import java.util.stream.Stream;

/**
 * This matcher matches the pair by the specified pairs from the csv file.
 * The csv file is given through the property of <code>{@link Configuration Configruation}</code> with key <code>pair.list</code>.
 *
 * <pre><code>
 * pochi.config().put("pair.list", "csv/file/path");
 * matcher = pochi.matcher("Specified");
 * ....
 * </code></pre>
 */
public class SpecifiedPairer<T extends Namer> extends AbstractPairer<T> {
    public static final PairerType TYPE_SIMPLE = new PairerType("specified-simple");
    public static final PairerType TYPE_FULLY = new PairerType("specified-fully");

    private final Relationer relationer;
    private final PairList pairs;

    public SpecifiedPairer(Configuration config, Relationer relationer) {
        super(config);
        this.pairs = PairListBuilder.build(config);
        this.relationer = relationer;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", getClass().getName(), relationer.getClass().getName());
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> birthmarks) {
        return birthmarks.stream()
                .flatMap(item -> makePair(item, birthmarks));
    }

    @Override
    public Stream<Pair<T, T>> pair(Streamable<T> birthmarks1, Streamable<T> birthmarks2) {
        return birthmarks1.stream()
                .flatMap(item -> makePair(item, birthmarks2));
    }

    private Stream<Pair<T, T>> makePair(T item, Streamable<T> birthmarks) {
        List<String> opponents = pairs.pairOf(item.name().name());
        return opponents.stream()
                .flatMap(baseItem -> makePairImpl(item, baseItem, birthmarks));
    }

    private Stream<Pair<T, T>> makePairImpl(T item, String opponent, Streamable<T> birthmarks) {
        return birthmarks.stream()
                .filter(targetItem -> relationer.isRelate(opponent, targetItem))
                .map(item2 -> new Pair<>(item, item2));
    }

    @Override
    public long count(Streamable<T> birthmarks) {
        return pair(birthmarks).count();
    }

    @Override
    public long count(Streamable<T> birthmarks1, Streamable<T> birthmarks2) {
        return pair(birthmarks1, birthmarks2).count();
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
        public String description() {
            return "pairs by the specified pairs. the pair list is given by the property of \"pair.list\"";
        }

        @Override
        public Pairer<T> build(Configuration config) {
            return new SpecifiedPairer<>(config, relationer);
        }
    }
}
