package jp.cafebabe.clpond.entities;

import jp.cafebabe.clpond.source.PathResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class PathEntry implements Entry {
    private Path path;
    private PathResolver source;

    public PathEntry(Path path, PathResolver source){
        this.path = path;
        this.source = source;
    }

    @Override
    public Path path(){
        return path;
    }

    @Override
    public ClassName className() {
        return source.parseClassName(path);
    }

    @Override
    public InputStream openStream() throws IOException{
        return source.openStream(path);
    }

    @Override
    public boolean endsWith(String suffix){
        return path.toString()
                .endsWith(suffix);
    }

    @Override
    public boolean isName(String name){
        return path.endsWith(name);
    }

    @Override
    public String toString(){
        return String.format("%s <%s>", loadFrom(), isClass() && className() != null? className(): path());
    }
}
