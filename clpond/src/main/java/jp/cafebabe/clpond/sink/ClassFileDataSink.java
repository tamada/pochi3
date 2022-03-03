package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClassFileDataSink extends AbstractDataSink {
    private Path path;

    public ClassFileDataSink(Path path){
        this.path = path;
    }

    Path path(){
        return path;
    }

    @Override
    public void consume(InputStream in, Entry entry) throws IOException {
        createDirectories(path().getParent());
        Files.copy(in, path());
    }

    protected void createDirectories(Path path){
        if(!Files.exists(path))
            createDirectoriesImpl(path);
    }

    private void createDirectoriesImpl(Path path){
        try{ Files.createDirectories(path); }
        catch(IOException e){ }
    }
}
