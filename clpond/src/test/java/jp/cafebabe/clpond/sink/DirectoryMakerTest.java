package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.PathHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryMakerTest {
    @Test
    public void testBasic(){
        FileSystem system = FileSystems.getDefault();
        DirectoryMaker.mkdirs(Paths.get("a/b/c"), system);

        assertTrue(Files.isDirectory(Paths.get("a/b/c")));
        assertFalse(Files.exists(Paths.get("a/b/c/d")));
        
        DirectoryMaker.mkdirs(Paths.get("a/b/c/d/e"), system);
        
        assertTrue(Files.isDirectory(Paths.get("a/b/c/d/e")));
    }

    @AfterEach
    public void tearDown(){
        PathHelper.deleteAll(Paths.get("a"));
    }
}
