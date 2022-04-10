package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Command(name = "compare", description = "compares the given birthmarks")
public class CompareCommand extends AbstractCommand {
    @Option(names = {"-a", "--algorithms"}, paramLabel = "ALGORITHMs...", description = "specify the comparing algorithm.", required = true)
    private String algorithm;

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent, the results outputs to stdout.")
    private Optional<String> dest = Optional.empty();

    @Option(names = {"--overwrite"}, description = "Failed to output the result if this option is absent and the destination is exists")
    private boolean overwrite;

    @Parameters(paramLabel = "BIRTHMARKs...", arity = "1..*")
    private List<Path> birthmarks;

    public PrintWriter dest() {
        return DestCreator.createDest(dest)
                .peekLeft(message -> push(message))
                .get();
    }

    @Override
    public Integer call() {
        System.out.println("compare");
        return 0;
    }
}
