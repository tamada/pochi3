package jp.cafebabe.birthmarks.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rules {
    private final List<Rule> list;

    public Rules(){
        this(Stream.empty());
    }

    public Rules(Stream<Rule> stream){
        list = stream.collect(
                Collectors.toList());
    }

    public boolean match(String className){
        return list.stream()
                .anyMatch(rule -> rule.match(className));
    }

    public Stream<Rule> stream(){
        return list.stream();
    }
}
