package jp.cafebabe.pochi.pairers;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.io.ResourceFinder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PairListBuilder implements Serializable {
    public static final String CONFIG_KEY = "pairer.list";

    public static final PairList build(Configuration config) {
        return new PairListBuilder().buildImpl(ResourceFinder.find(config.value(CONFIG_KEY, null)));
    }

    private PairList buildImpl(Optional<URL> path) {
        return new PairList(readPairs(path));
    }

    private Map<String, List<String>> readPairs(Optional<URL> path) {
        return path.map(p -> readPairs(p))
                .orElseGet(() -> new HashMap<>());
    }

    private Map<String, List<String>> readPairs(URL url) {
        return Try.withResources(() -> new BufferedReader(new InputStreamReader(url.openStream())))
                .of(in -> readPairsImpl(in))
                .getOrElse(() -> new HashMap<>());
    }

    private Map<String, List<String>> readPairsImpl(BufferedReader in) {
        return in.lines()
                .filter(line -> !line.trim().startsWith("#"))
                .map(line -> line.split(","))
                .collect(Collectors.toMap(items -> items[0], items -> List.of(items[1]),
                        (before, after) -> merge(before, after)));
    }

    private List<String> merge(List<String> before, List<String> after) {
        return Stream.concat(before.stream(), after.stream())
                .collect(Collectors.toList());
    }
}
