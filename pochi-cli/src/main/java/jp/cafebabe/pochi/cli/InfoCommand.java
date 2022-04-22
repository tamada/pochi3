package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.TaskBuilder;
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

@Command(name = "info", description = "print information of processors in pochi.")
public class InfoCommand implements Callable<Integer> {
    private static List<String> availables = List.of("all", "extractors", "pairers", "algorithms", "relationers", "rules", "properties", "runtimes");

    @Option(names = {"-i", "--info"}, paramLabel = "TARGET", description = "specify the printing information. Availables: all, extractors, pairers, relationers, algorithms, rules, properties, and runtimes. Default: all",
        arity = "0..*")
    private List<String> targets = List.of("all");

    @ParentCommand
    private Pochi pochi;

    @Override
    @SuppressWarnings("unchecked")
    public Integer call() {
        targets = validateTargets(targets);
        isRequest("extractors", () -> printBuilders("Extractors", new ExtractorBuilderFactory()));
        isRequest("pairers", () -> printBuilders("Pairers", new ServiceBuilderFactory<>(PairerBuilder.class)));
        isRequest("relationers", () -> printBuilders("Relationers", new RelationerBuilderFactory()));
        isRequest("algorithms", () -> printBuilders("Comparison Algorithms", new ServiceBuilderFactory<>(ComparatorBuilder.class)));
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

    private <TB extends TaskBuilder<E, T>, T extends Stringer, E> void printBuilders(String label, BuilderFactory<TB, T> factory) {
        pochi.push(header(label));
        factory.builders()
                .map(t -> String.format("%-20s %s", t.type().string(), t.description()))
                .forEach(pochi::push);
    }

    private String header(String label) {
        return AnsiColors.GREEN.decoratef("========== %s ==========", label);
    }
}
