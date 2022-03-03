package jp.cafebabe.clpond.source;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DirectoryTraverserTest {
    private DirectoryTraverser traverser = new DirectoryTraverser();

    @Test
    public void testBasic() throws Exception{
        List<Path> list = traverser.traverse(Paths.get("src/test/resources/hello/target/classes"));

        assertThat(list.size(), is(2));
    }

    @Test
    public void testNotExistPath() throws Exception{
        List<Path> list = traverser.traverse(Paths.get("not/exist/path"));

        assertThat(list.size(), is(0));
    }

    @Test
    public void testNullValue() throws Exception{
        List<Path> list = traverser.traverse(new Path[] { null });

        assertThat(list.size(), is(0));
    }
}
