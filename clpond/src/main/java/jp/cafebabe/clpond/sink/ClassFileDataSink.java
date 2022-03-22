package jp.cafebabe.clpond.sink;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClassFileDataSink extends AbstractDataSink {
    private final Path path;

    public ClassFileDataSink(Path path){
        this.path = path;
    }

    Path path(){
        return path;
    }

    @Override
    public void consume(InputStream in, Entry entry) throws IOException {
        if(createDirectories(path().getParent()))
            Files.copy(in, path());
    }

    protected boolean createDirectories(Path path){
        if(!Files.exists(path))
            return createDirectoriesImpl(path);
        return false;
    }

    private boolean createDirectoriesImpl(Path path){
        return Try.of(() -> Files.createDirectories(path))
                .isSuccess();
    }
}
