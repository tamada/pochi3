package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.extractors.Extractor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "perform", description = "extract birthmarks and compare the extraction results by a round-robin")
public class PerformCommand extends ExtractCommand implements Callable<Integer> {
    @Option(names = {"-a", "--algorithm"}, paramLabel = "ALGORITHM", description = "specify the comparing algorithm. info command shows availables", required = true)
    private String algorithm;

    private CompareCommand compareCommand = new CompareCommand();

    int perform(Configuration config, Extractor extractor) {
        var birthmarks = extractImpl(extractor);
        var comparator = compareCommand.constructComparator(config, algorithm);
        var pairer = compareCommand.constructPairer(config);
        comparator.map(c -> c.compare(birthmarks, pairer))
                .ifPresent(compareCommand::printResults);
        return 0;
    }
}
