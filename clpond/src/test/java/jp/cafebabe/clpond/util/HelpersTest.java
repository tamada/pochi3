package jp.cafebabe.clpond.util;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.DirectoryMaker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpersTest {
    @Test
    public void testMeaninglessPathsHelper() {
        PathHelper helper = new PathHelper();
        assertTrue(helper != null);
    }

    @Test
    public void testMeaninglessDirectoryMaker() {
        DirectoryMaker maker = new DirectoryMaker();
        assertTrue(maker != null);
    }
}
