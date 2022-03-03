package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface PathResolver {
    InputStream openStream(Path path) throws IOException;

    ClassName parseClassName(Path path);

    default ClassName resolveClassName(Path path) throws IOException{
        return ClassNameExtractVisitor.extractClassName(openStream(path));
    }
}
