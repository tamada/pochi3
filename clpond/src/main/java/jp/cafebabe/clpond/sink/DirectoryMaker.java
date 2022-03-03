package jp.cafebabe.clpond.sink;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.PathHelper;

public class DirectoryMaker {
    public static void mkdirs(Path givenPath, FileSystem givenSystem){
        Try.run(() -> mkdirsImpl(givenPath, givenSystem));
    }

    private static void mkdirsImpl(Path path, FileSystem system) throws IOException{
        if(path == null || PathHelper.exists(path, system)) return;
        mkdirsImpl(path.getParent(), system);
        system.provider().createDirectory(path);
    }
}
