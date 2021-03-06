package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.Comparisons;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.io.ComparisonsJsonier;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.pochi.pairers.PairerBuilderFactory;
import jp.cafebabe.pochi.utils.ServiceBuilderFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Command(name = "compare", description = "compares the given birthmarks by a round-robin.")
public class CompareCommand implements Callable<Integer> {
    @Option(names = {"-a", "--algorithm"}, paramLabel = "ALGORITHM", description = "specify the comparing algorithm. Available: show info subcommand.", required = true)
    private String algorithm;

    @Option(names = {"-p", "--pairer"}, paramLabel = "PAIRER", defaultValue = "round_robin", description = "specify the pairer for the birthmarks")
    private String pairerName = "round_robin";

    @Option(names = {"-d", "--dest"}, paramLabel = "DEST", description = "specify the destination. If this option is absent or dash (\"-\"), output to stdout.")
    private Optional<String> dest = Optional.empty();

    @Parameters(paramLabel = "BIRTHMARKs", arity = "0..*", description = "birthmarks in json format. If parameters were empty or dash (\"-\"), read from stdin.")
    private List<Path> birthmarks = new ArrayList<>();

    @ParentCommand
    private Pochi pochi;

    Pairer<Birthmark> constructPairer(Configuration config) {
        return new PairerBuilderFactory<Birthmark>().builder(pairerName)
                .orElseThrow(() -> new InternalError(String.format("%s: pairer not found", pairerName)))
                .build(config);
    }

    Optional<Comparator> constructComparator(Configuration config, String algorithm) {
        return new ServiceBuilderFactory<>(ComparatorBuilder.class)
                .builder(algorithm)
                .map(b -> b.build(config));
    }

    void printResults(Comparisons comparisons) {
        new DestCreator(pochi)
                .dest(dest, p -> p.println(new ComparisonsJsonier().toJson(comparisons)));
        if(comparisons.hasFailure())
            pochi.flush();
    }

    @Override
    public Integer call() {
        var loader = new BirthmarkLoader(pochi);
        var config = pochi.config();
        var comparator = constructComparator(config, algorithm);
        comparator.map(c -> c.compare(loader.load(birthmarks), constructPairer(config)))
                .ifPresent(this::printResults);
        return 0;
    }
}
