package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.utils.Stringer;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.pairers.relationers.RelationerBuilderFactory;
import jp.cafebabe.pochi.utils.ServiceBuilderFactory;
import picocli.CommandLine.Command;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Command(name = "info", description = "print information of processors in pochi")
public class InfoCommand extends AbstractCommand {
    @Override
    public Integer call() {
        printTypes("Extractors", new ExtractorBuilderFactory());
        printPairerTypes();
        printTypes("Comparators", new ServiceBuilderFactory<>(ComparatorBuilder.class));
        printTypes("Relationers", new RelationerBuilderFactory());
        return 0;
    }

    private <B, T extends Stringer> void printTypes(String label, BuilderFactory<B, T> factory) {
        printStream(label, factory.availables());
    }

    @SuppressWarnings("unchecked")
    private void printPairerTypes() {
        printStream("Pairers", new ServiceBuilderFactory(PairerBuilder.class)
                .availables());
    }

    private <T> void printStream(String label, Stream<? extends Stringer> stream) {
        System.out.printf("========== %s ==========%n", label);
        System.out.println(stream.map(t -> t.string())
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
