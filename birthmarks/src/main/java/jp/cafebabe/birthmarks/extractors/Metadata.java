package jp.cafebabe.birthmarks.extractors;

import java.io.Serializable;
import java.net.URI;

public class Metadata implements Serializable {
    private static final long serialVersionUID = 7374573083002271494L;

    private Name name;
    private URI location;
    private BirthmarkType type;

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
