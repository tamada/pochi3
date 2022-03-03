package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jp.cafebabe.clpond.util.AssertHelper.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectoryDataSourceFactoryTest {
    private Path jarfile;
    private Path directory;
    private DataSourceFactory factory = new DirectoryDataSourceFactory();

    @BeforeEach
    public void setUp(){
        jarfile = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar");
        directory = Paths.get("src/test/resources/hello/target/classes");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(directory), is(true));
        assertThat(factory.isTarget(jarfile), is(false));

        factory.build(new File(directory.toString()));
    }

    @Test
    public void testThrows() throws Exception{
        assertThrows(UnsupportedDataSourceException.class, 
                () -> factory.build(jarfile));
    }
}
