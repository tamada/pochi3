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
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(name = "info", description = "print information of processors in pochi")
public class InfoCommand implements Callable<Integer> {
    private static List<String> availables = List.of("all", "extractors", "pairers", "algorithms", "relationers", "rules", "properties", "runtimes");

    @Option(names = {"-i", "--info"}, description = "specify the printing information. Availables: all, extractors, pairers, algorithms, relationers, rules, properties, and runtimes. Default: all",
        arity = "0..*")
    private List<String> targets = List.of("all");

    @ParentCommand
    private Pochi pochi;

    @Override
    public Integer call() {
        targets = validateTargets(targets);
        isRequest("extractors", () -> printTypes("Extractors", new ExtractorBuilderFactory()));
        isRequest("pairers", () -> printPairerTypes());
        isRequest("algorithms", () -> printTypes("Algorithms", new ServiceBuilderFactory<>(ComparatorBuilder.class)));
        isRequest("relationers", () -> printTypes("Relationers", new RelationerBuilderFactory()));
        printConfig();
        isRequest("engines", () -> printScriptEngine());
        return pochi.flush();
    }

    private List<String> validateTargets(List<String> targets) {
        checkWarnings(targets);
        return targets.stream()
                .flatMap(str -> Arrays.stream(str.split(",")))
                .map(String::toLowerCase)
                .distinct().toList();
    }

    private void checkWarnings(List<String> targets) {
        targets.stream()
                .flatMap(item -> Arrays.stream(item.split(",")))
                .filter(item -> !availables.contains(item.toLowerCase()))
                .forEach(item -> pochi.pushf(AnsiColors.YELLOW_BOLD, "Warning: %s: unknown information type", item));
    }

    private void isRequest(String type, Runnable action) {
        if(targets.contains(type) || targets.contains("all"))
            action.run();
    }

    private void printScriptEngine() {
        pochi.push(header("Runtime Environment"));
        var manager = new ScriptEngineManager();
        manager.getEngineFactories().stream()
                .forEach(this::printFactory);
    }

    private void printFactory(ScriptEngineFactory factory) {
        pochi.pushf("%s %s (%s %s)", factory.getLanguageName(),
                factory.getLanguageVersion(), factory.getEngineName(), factory.getEngineVersion());
    }

    private void printConfig() {
        Try.of(() -> pochi.config())
                .onSuccess(this::printConfigImpl)
                .onFailure(pochi::push);
    }

    private void printConfigImpl(Configuration c) {
        isRequest("rules", () -> printRules(c));
        isRequest("properties", () -> printProperties(c));
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
        return AnsiColors.GREEN.decoratef("========== %s ==========", label);
    }
}
