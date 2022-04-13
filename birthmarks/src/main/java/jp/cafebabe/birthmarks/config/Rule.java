package jp.cafebabe.birthmarks.config;

import java.util.StringJoiner;

public record Rule(Position position, Snippet snippet) {
    public Rule(String position, String snippet){
        this(Position.valueOf(position.toUpperCase()),
                new Snippet(snippet));
    }

    public boolean match(String string){
        return position.match(snippet, string);
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(position().name());
        joiner.add(snippet().value());
        return joiner.toString();
    }
}
