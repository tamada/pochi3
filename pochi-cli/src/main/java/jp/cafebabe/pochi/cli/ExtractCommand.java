package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.cli.messages.AnsiColors;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Command(name = "extract", description = "extract the birthmarks from given targets")
public class ExtractCommand extends AbstractCommand {
    @Option(names = {"-b", "--birthmark"}, paramLabel = "BIRTHMARK", description = "specify the birthmark type. This option is mandatory", required = true)
    private String birthmarkType;

    @Option(names = {"-c", "--container"}, paramLabel = "CONTAINER", description = "specify the container type. Available: list, set, vector, graph. Default: list")
    private ContainerType type = ContainerType.List;

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent, the results outputs to stdout.")
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

    private boolean validateOptions(ExtractorBuilderFactory factory) {
        boolean result = false;
        if(!factory.available(birthmarkType)){
            push(AnsiColors.RED_BOLD.decoratef("Error: %s: birthmark type not found", birthmarkType));
            result = true;
        }
        return result || targets.stream()
                .filter(this::hasSomeError)
                .peek(this::pushErrorMessage)
                .count() > 0;
    }

    private boolean hasSomeError(Path path) {
        return !Files.exists(path);
    }

    private void pushErrorMessage(Path path) {
        push(AnsiColors.RED_BOLD.decoratef("Error: %s: file not found", path));
    }

    private int perform(ExtractorBuilder builder) {
        DataSourceBuilder dsBuilder = DataSourceBuilder.instance();
        return 0;
    }

    private Birthmarks performEach(Path target, DataSourceBuilder dsBuilder, ExtractorBuilder builder) {
        return null;
    }

    @Override
    public Integer call() {
        ExtractorBuilderFactory factory = new ExtractorBuilderFactory();
        if(!validateOptions(factory)) {
            return 1;
        }
        return factory.builder(birthmarkType)
                .map(this::perform)
                .orElse(2);
    }
}
