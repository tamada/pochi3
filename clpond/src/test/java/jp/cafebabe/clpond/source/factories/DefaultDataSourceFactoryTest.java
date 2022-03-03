package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultDataSourceFactoryTest {
    private DataSourceFactory factory = new DefaultDataSourceFactory();
    private Path path = Paths.get("src/test/resources/hello/target/classes");

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(path), is(true));
        assertThat(factory.isTarget(null, null, null), is(false));
    }

    @Test
    public void testBasic2() throws Exception{
        assertThat(factory.isTarget(null), is(false));        
    }
}
