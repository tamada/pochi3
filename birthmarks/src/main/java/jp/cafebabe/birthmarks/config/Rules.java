package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.utils.Jsonable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rules implements Jsonable {
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

    @Override
    public String toJson() {
        return list.stream()
                .map(Rule::toJson)
                .collect(Collectors.joining(",", "{", "}"));
    }
}
