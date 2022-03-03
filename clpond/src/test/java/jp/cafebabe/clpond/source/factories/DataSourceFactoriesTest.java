package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static jp.cafebabe.clpond.util.AssertHelper.assertThrows;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Optional;

public class DataSourceFactoriesTest {
    private DataSourceFactories factories = new DataSourceFactories();

    @Test
    public void testDirectory() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("src/test/resources"), FileSystems.getDefault());

        assertThat(factory.isPresent(), is(true));
        assertThat(factory.get(), is(instanceOf(DirectoryDataSourceFactory.class)));
    }

    @Test
    public void testJar() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar"));

        assertThat(factory.isPresent(), is(true));
        assertThat(factory.get(), is(instanceOf(JarFileDataSourceFactory.class)));
    }

    @Test
    public void testNotExistsJar() throws Exception{
        assertThrows(IOException.class, 
                () -> factories.find(Paths.get("not/exists/jar")));
    }

}
