package jp.cafebabe.clpond.source;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTraverserTest {
    private final DirectoryTraverser traverser = new DirectoryTraverser();

    @Test
    public void testBasic() throws Exception{
        List<Path> list = traverser.traverse(Paths.get("src/test/resources/hello/target/classes"));

        assertEquals(2, list.size());
    }

    @Test
    public void testNotExistPath() {
        List<Path> list = traverser.traverse(Paths.get("not/exist/path"));

        assertEquals(0, list.size());
    }

    @Test
    public void testNullValue() {
        List<Path> list = traverser.traverse(new Path[] { null });

        assertEquals(0, list.size());
    }
}
