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
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PairListBuilder implements Serializable {
    public static final String CONFIG_KEY = "pairer.list";

    public static PairList build(Configuration config) {
        var resource = ResourceFinder.find(config.value(CONFIG_KEY, null));
        return resource.map(url -> new PairListBuilder().buildImpl(url))
                .orElseGet(PairList::new);
    }

    private PairList buildImpl(URL url) {
        return new PairList(readPairs(url));
    }

    private Map<String, List<String>> readPairs(URL url) {
        return Try.withResources(() -> new BufferedReader(new InputStreamReader(url.openStream())))
                .of(this::readPairsImpl)
                .getOrElse(HashMap::new);
    }

    private Map<String, List<String>> readPairsImpl(BufferedReader in) {
        return in.lines()
                .filter(line -> !line.trim().startsWith("#"))
                .map(line -> line.split(","))
                .collect(Collectors.toMap(items -> items[0], items -> List.of(items[1]),
                        this::merge));
    }

    private List<String> merge(List<String> before, List<String> after) {
        return Stream.concat(before.stream(), after.stream())
                .collect(Collectors.toList());
    }
}
