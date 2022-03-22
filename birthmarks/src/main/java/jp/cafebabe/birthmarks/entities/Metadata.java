package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.clpond.entities.Name;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

public class Metadata implements Serializable {
    @Serial
    private static final long serialVersionUID = 7374573083002271494L;

    private final Name name;
    private final URI location;
    private final BirthmarkType type;

    public Metadata(Name name, URI uri, BirthmarkType type) {
        this.name = name;
        this.location = uri;
        this.type = type;
    }

    public Name name() {
        return name;
    }

    public URI location() {
        return location;
    }

    public BirthmarkType type() {
        return type;
    }

    public void accept(BirthmarkVisitor visitor) {
        visitor.visitMetadata(name, location, type);
    }
}
