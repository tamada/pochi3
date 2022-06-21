package jp.cafebabe.clpond.entities;

import java.io.Serial;
import java.io.Serializable;

public record ClassName(String name) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1305904437135129418L;

    public ClassName(Name name) {
        this(name.name());
    }

    public ClassName(String name){
        this.name = name.replace('/', '.')
                .replace('\\', '.');
    }

    public String fqdnName() {
        return name;
    }

    public String name() {
        int index = name.lastIndexOf('.');
        if(index >= 0 && index != (name.length() - 1))
            return name.substring(index + 1);
        return name;
    }
}
