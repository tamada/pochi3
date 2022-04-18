package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.io.BirthmarksJsonier;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.cli.messages.AnsiColors;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(name = "extract", description = "extract the birthmarks from given targets")
public class ExtractCommand implements Callable<Integer> {
    @Option(names = {"-b", "--birthmark"}, paramLabel = "BIRTHMARK", description = "specify the birthmark type. This option is mandatory", required = true)
    private String birthmarkType;

    @Option(names = {"-c", "--container"}, paramLabel = "CONTAINER", description = "specify the container type. Available: list, set, vector, graph. Default: list")
    private ContainerType type = ContainerType.List;

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent or dash (\"-\"), output to stdout.")
    private Optional<String> dest = Optional.empty();

    @Parameters(paramLabel = "CLASS|ZIP|JAR|WAR_FILEs...", arity = "1..*", description = "targets of birthmark extraction")
    private List<Path> targets;

    @ParentCommand
    private Pochi pochi;

    private boolean isValidOptions(ExtractorBuilderFactory factory) {
        boolean result = true;
        if(!factory.available(birthmarkType)){
            pochi.pushf(AnsiColors.RED_BOLD, "Error: %s: birthmark type not found", birthmarkType);
            result = false;
        }
        return result & targets.stream()
                .filter(p -> !Files.exists(p))
                .peek(this::pushErrorMessage)
                .count() > 0;
    }

    private void pushErrorMessage(Path path) {
        pochi.pushf(AnsiColors.RED_BOLD, "Error: %s: file not found", path);
    }

    int perform(Configuration config, Extractor extractor) {
        var birthmarks = extractImpl(extractor);
        new DestCreator(pochi)
                .dest(dest, p -> p.println(new BirthmarksJsonier().toJson(birthmarks)));
        if(birthmarks.hasFailure())
            printFailures(birthmarks.failures());
        return pochi.flush();
    }

    private void printFailures(Stream<Throwable> stream) {
        pochi.push(AnsiColors.RED_BOLD, "========== Errors ==========");
        stream.forEach(t -> pochi.push(t));
    }

    Birthmarks extractImpl(Extractor extractor) {
        DataSourceBuilder dsBuilder = DataSourceBuilder.instance();
        return targets.stream()
                .map(path -> performEach(path, dsBuilder, extractor))
                .reduce(new Birthmarks(Stream.empty()), (b1, b2) -> b1.merge(b2));
    }

    private Birthmarks performEach(Path target, DataSourceBuilder dsBuilder, Extractor extractor) {
        return Try.of(() -> dsBuilder.build(target))
                .map(source -> extractor.extract(source, type))
                .onFailure(t -> pochi.push(t))
                .get();
    }

    @Override
    public Integer call() {
        ExtractorBuilderFactory factory = new ExtractorBuilderFactory();
        if(isValidOptions(factory))
            return pochi.flush(1);
        var config = pochi.config();
        return factory.builder(birthmarkType)
                .map(builder -> perform(config, builder.build(config)))
                .orElse(2);
    }
}
