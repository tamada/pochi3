package jp.cafebabe.clpond.util;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.DirectoryMaker;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelpersTest {
    @Test
    public void testMeaninglessPathsHelper() throws Exception{
        PathHelper helper = new PathHelper();
        assertThat(helper, is(not(nullValue())));
    }

    @Test
    public void testMeaninglessDirectoryMaker() throws Exception{
        DirectoryMaker maker = new DirectoryMaker();
        assertThat(maker, is(not(nullValue())));
    }
}
