package jp.cafebabe.pochi.pairers;

import java.util.List;
import java.util.Map;

public class PairList {
    private final Map<String, List<String>> pairs;

    PairList() {
        this(Map.of());
    }

    PairList(Map<String, List<String>> pairs) {
        this.pairs = pairs;
    }

    public List<String> pairOf(String name) {
        return pairs.getOrDefault(name, List.of());
    }
}
