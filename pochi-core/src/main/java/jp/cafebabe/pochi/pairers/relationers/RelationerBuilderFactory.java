package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Relationer;
import jp.cafebabe.birthmarks.pairers.RelationerType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class RelationerBuilderFactory implements BuilderFactory<TaskBuilder<Relationer, RelationerType>, RelationerType> {
    private final List<TaskBuilder<Relationer, RelationerType>> relationers =
            List.of(new FullyMatchRelationer.Builder(),
                    new ClassNameRelationer.Builder());

    public Optional<TaskBuilder<Relationer, RelationerType>> builder(Configuration config) {
        return builder(config.value("pairer.relationer"));
    }

    @Override
    public Optional<TaskBuilder<Relationer, RelationerType>> builder(String type) {
        return builders().filter(tb -> tb.matchType(type))
                .findFirst()
                .or(() -> Optional.of(new NeverRelationer.Builder()));
    }

    @Override
    public Stream<TaskBuilder<Relationer, RelationerType>> builders() {
        return relationers.stream();
    }

    @Override
    public Stream<RelationerType> availables() {
        return relationers.stream().map(TaskBuilder::type);
    }
}
