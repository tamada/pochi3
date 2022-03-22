package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.utils.Jsonable;

import java.util.StringJoiner;

public record Rule(Position position, Snippet snippet) implements Jsonable {
    public Rule(String position, String snippet){
        this(Position.valueOf(position.toUpperCase()),
                new Snippet(snippet));
    }

    public boolean match(String string){
        return position.match(snippet, string);
    }

    public String toJson() {
        return String.format("{\"type\":\"%s\",\"pattern\":\"%s\"}",
                position().name(), snippet().value());
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(position().name());
        joiner.add(snippet().value());
        return joiner.toString();
    }
}
