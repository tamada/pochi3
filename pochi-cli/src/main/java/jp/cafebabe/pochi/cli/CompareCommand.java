package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.Comparisons;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.io.BirthmarkParser;
import jp.cafebabe.birthmarks.io.ComparisonsJsonier;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.pochi.pairers.PairerBuilderFactory;
import jp.cafebabe.pochi.utils.ServiceBuilderFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Command(name = "compare", description = "compares the given birthmarks by a round-robin")
public class CompareCommand extends AbstractCommand {
    @Option(names = {"-a", "--algorithms"}, paramLabel = "ALGORITHMs...", description = "specify the comparing algorithm. info command shows availables", required = true)
    private String algorithm;

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent or dash (\"-\"), output to stdout .")
    private Optional<String> dest = Optional.empty();

    @Option(names = {"--overwrite"}, description = "Failed to output the result if this option is absent and the destination is exists")
    private boolean overwrite;

    @Parameters(paramLabel = "BIRTHMARKs...", arity = "0..*", description = "birthmarks in json format. If parameters were empty or dash (\"-\"), read from stdin")
    private List<Path> birthmarks = new ArrayList<>();

    public CompareCommand() {
        this(new GlobalOptions());
    }

    public CompareCommand(GlobalOptions globalOptions) {
        super(globalOptions);
    }

    private Birthmarks loadBirthmarks() {
        BirthmarkParser parser = new BirthmarkParser();
        if (isReadFromStdin())
            return readFromStdin(parser);
        return readFromPaths(parser);
    }

    private boolean isReadFromStdin() {
        return birthmarks.size() == 0 ||
                (birthmarks.size() == 1 && birthmarks.contains(Path.of("-")));
    }

    private Birthmarks readFromStdin(BirthmarkParser parser) {
        return Try.of(() -> parser.parse(System.in))
                .onFailure(this::push)
                .get();
    }

    private Birthmarks readFromPaths(BirthmarkParser parser) {
        return birthmarks.stream()
                .map(p -> readJson(parser, p))
                .reduce(new Birthmarks(Stream.empty()), (b1, b2) -> b1.merge(b2));
    }

    private Birthmarks readJson(BirthmarkParser parser, Path path) {
        return Try.withResources(() -> Files.newInputStream(path))
                .of(in -> parser.parse(in))
                .onFailure(this::push)
                .get();
    }

    private Pairer<Birthmark> constructPairer(Configuration config) {
        return new PairerBuilderFactory<Birthmark>().builder("round_robin")
                .orElseThrow(() -> new InternalError("round_robin: pairer not found"))
                .build(config);
    }

    private Optional<Comparator> constructComparator(Configuration config) {
        return new ServiceBuilderFactory<>(ComparatorBuilder.class)
                .builder(algorithm)
                .map(b -> b.build(config));
    }

    private void printResults(Comparisons comparisons) {
        new DestCreator(this)
                .dest(dest, p -> p.println(new ComparisonsJsonier().toJson(comparisons)));
        if(comparisons.hasFailure())
            printAll();
    }

    @Override
    public Integer call() {
        var config = globalOptions.config();
        var comparator = constructComparator(config);
        comparator.map(c -> c.compare(loadBirthmarks(), constructPairer(config)))
                .ifPresent(this::printResults);
        return 0;
    }
}
