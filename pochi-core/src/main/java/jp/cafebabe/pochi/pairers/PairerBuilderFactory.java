package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.pairers.PairerType;
import jp.cafebabe.birthmarks.utils.Namer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PairerBuilderFactory<E extends Namer> implements BuilderFactory<PairerBuilder<E>, PairerType> {
    private List<PairerBuilder<E>> list = List.of(new GuessedPairer.Builder<>(),
            new RoundRobinPairer.Builder<>(), new RoundRobinPairer.WithSamePairBuilder<>(),
            new SpecifiedPairer.Builder<>());

    @Override
    public Optional<PairerBuilder<E>> builder(String type) {
        return builders()
                .filter(b -> b.matchType(type))
                .findFirst();
    }

    @Override
    public Stream<PairerBuilder<E>> builders() {
        return list.stream();
    }

    @Override
    public Stream<PairerType> availables() {
        return builders()
                .map(b -> b.type());
    }
}
