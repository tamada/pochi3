package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.pochi.cli.time.Watch;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.PrintWriter;
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

    public PrintWriter dest() {
        return DestCreator.createDest(dest)
                .peekLeft(message -> push(message))
                .get();
    }

    @Override
    public Integer call() {
        return 0;
    }
}
