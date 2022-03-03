package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.PathHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryMakerTest {
    @Test
    public void testBasic(){
        FileSystem system = FileSystems.getDefault();
        DirectoryMaker.mkdirs(Paths.get("a/b/c"), system);

        assertThat(Files.isDirectory(Paths.get("a/b/c")), is(true));
        assertThat(Files.exists(Paths.get("a/b/c/d")), is(false));
        
        DirectoryMaker.mkdirs(Paths.get("a/b/c/d/e"), system);
        
        assertThat(Files.isDirectory(Paths.get("a/b/c/d/e")), is(true));
    }

    @AfterEach
    public void tearDown(){
        PathHelper.deleteAll(Paths.get("a"));
    }
}
