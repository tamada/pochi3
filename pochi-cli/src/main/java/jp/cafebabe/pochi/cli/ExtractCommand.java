package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.cli.messages.AnsiColors;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Command(name = "extract", description = "extract the birthmarks from given targets")
public class ExtractCommand extends AbstractCommand {
    @Option(names = {"-b", "--birthmark"}, paramLabel = "BIRTHMARK", description = "specify the birthmark type. This option is mandatory", required = true)
    private String birthmarkType;

    @Option(names = {"-c", "--container"}, paramLabel = "CONTAINER", description = "specify the container type. Available: list, set, vector, graph. Default: list")
    private ContainerType type = ContainerType.List;

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent or dash (\"-\"), the results outputs to stdout.")
    private Optional<String> dest = Optional.empty();

    @Parameters(paramLabel = "CLASS|ZIP|JAR|WAR_FILEs...", arity = "1..*", description = "targets of birthmark extraction")
    private List<Path> targets;

    public ExtractCommand() {
        this(new GlobalOptions());
    }

    public ExtractCommand(GlobalOptions globalOptions) {
        super(globalOptions);
    }

    public PrintWriter dest() {
        return DestCreator.createDest(dest)
                .peekLeft(message -> push(message))
                .get();
    }

    private void dest(Consumer<PrintWriter> action) {
        var d = dest();
        action.accept(d);
        d.flush();
    }

    private boolean isValidOptions(ExtractorBuilderFactory factory) {
        boolean result = true;
        if(!factory.available(birthmarkType)){
            pushf(AnsiColors.RED_BOLD, "Error: %s: birthmark type not found", birthmarkType);
            result = false;
        }
        return result & targets.stream()
                .filter(p -> !Files.exists(p))
                .peek(this::pushErrorMessage)
                .count() > 0;
    }

    private void pushErrorMessage(Path path) {
        pushf(AnsiColors.RED_BOLD, "Error: %s: file not found", path);
    }

    private int perform(Extractor extractor) {
        var birthmarks = extractImpl(extractor);
        dest(p -> p.println(birthmarks.toJson()));
        if(birthmarks.hasFailure())
            printFailures(birthmarks.failures());
        return printAll();
    }

    private void printFailures(Stream<Throwable> stream) {
        push(AnsiColors.RED_BOLD, "========== Errors ==========");
        stream.forEach(t -> push(t));
    }

    private Birthmarks extractImpl(Extractor extractor) {
        DataSourceBuilder dsBuilder = DataSourceBuilder.instance();
        return targets.stream()
                .map(path -> performEach(path, dsBuilder, extractor))
                .reduce(new Birthmarks(Stream.empty()), (b1, b2) -> b1.merge(b2));
    }

    private Birthmarks performEach(Path target, DataSourceBuilder dsBuilder, Extractor extractor) {
        return Try.of(() -> dsBuilder.build(target))
                .map(source -> extractor.extract(source, type))
                .onFailure(t -> push(t))
                .get();
    }

    @Override
    public Integer call() {
        ExtractorBuilderFactory factory = new ExtractorBuilderFactory();
        if(isValidOptions(factory))
            return printAll(1);
        var config = globalOptions.config();
        return factory.builder(birthmarkType)
                .map(builder -> perform(builder.build(config)))
                .orElse(2);
    }
}
