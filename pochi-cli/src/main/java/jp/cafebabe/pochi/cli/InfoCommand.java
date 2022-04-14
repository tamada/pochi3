package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.PairerBuilder;
import jp.cafebabe.birthmarks.utils.Stringer;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.cli.messages.AnsiColors;
import jp.cafebabe.pochi.pairers.relationers.RelationerBuilderFactory;
import jp.cafebabe.pochi.utils.ServiceBuilderFactory;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(name = "info", description = "print information of processors in pochi")
public class InfoCommand implements Callable<Integer> {
    @ParentCommand
    private Pochi pochi;

    @Override
    public Integer call() {
        printTypes("Extractors", new ExtractorBuilderFactory());
        printPairerTypes();
        printTypes("Algorithms", new ServiceBuilderFactory<>(ComparatorBuilder.class));
        printTypes("Relationers", new RelationerBuilderFactory());
        printConfig();
        return pochi.printAll();
    }

    private void printConfig() {
        Try.of(() -> pochi.config())
                .onSuccess(this::printConfigImpl)
                .onFailure(pochi::push);
    }

    private void printConfigImpl(Configuration c) {
        printRules(c);
        printProperties(c);
    }

    private void printRules(Configuration config) {
        pochi.push(header("Rules"));
        config.rules()
                .forEach(r -> pochi.push(r.toString()));
    }

    private void printProperties(Configuration config) {
        pochi.push(header("Properties"));
        config.properties()
                .forEach(p -> p.accept((label, value) -> pochi.pushf("%s: %s", label, value)));
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
        pochi.push(header(label));
        stream.map(t -> t.string())
                .forEach(pochi::push);
    }

    private String header(String label) {
        return AnsiColors.YELLOW.decoratef("========== %s ==========", label);
    }
}
