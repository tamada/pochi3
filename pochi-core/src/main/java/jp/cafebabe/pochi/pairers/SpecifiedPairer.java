package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.pochi.pairers.relationers.RelationerBuilder;

import java.util.List;
import java.util.stream.Stream;

/**
 * This matcher matches the pair by the specified pairs from the csv file.
 * The csv file is given through the property of {@link Configuration <code>Configruation</code>} with key <code>pair.list</code>.
 *
 * <pre><code>
 * pochi.config().put("pair.list", "csv/file/path");
 * matcher = pochi.matcher("Specified");
 * ....
 * </code></pre>
 */
public class SpecifiedPairer extends AbstractPairer {
    public static final PairerType TYPE = new PairerType("Specified");

    private Relationer relationer;
    private PairList pairs;

    public SpecifiedPairer(Configuration config) {
        super(config);
        this.pairs = PairListBuilder.build(config);
        this.relationer = RelationerBuilder.build(config);
    }

    @Override
    public Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks birthmarks) {
        return birthmarks.stream()
                .flatMap(item -> makePair(item, birthmarks));
    }

    @Override
    public Stream<Pair<Birthmark, Birthmark>> pair(Birthmarks birthmarks1, Birthmarks birthmarks2) {
        return birthmarks1.stream()
                .flatMap(item -> makePair(item, birthmarks2));
    }

    private Stream<Pair<Birthmark, Birthmark>> makePair(Birthmark item, Birthmarks birthmarks) {
        List<String> opponents = pairs.pairOf(item.name().name());
        return opponents.stream()
                .flatMap(baseItem -> makePairImpl(item, baseItem, birthmarks));
    }

    private Stream<Pair<Birthmark, Birthmark>> makePairImpl(Birthmark item, String opponent, Birthmarks birthmarks) {
        return birthmarks.stream()
                .filter(targetItem -> relationer.isRelate(opponent, targetItem))
                .map(item2 -> new Pair<>(item, item2));
    }

    @Override
    public long count(Birthmarks birthmarks) {
        return pair(birthmarks).count();
    }

    @Override
    public long count(Birthmarks birthmarks1, Birthmarks birthmarks2) {
        return pair(birthmarks1, birthmarks2).count();
    }

    public static final class Builder implements PairerBuilder {
        @Override
        public PairerType type() {
            return TYPE;
        }

        @Override
        public Pairer build(Configuration config) {
            return new SpecifiedPairer(config);
        }
    }
}
