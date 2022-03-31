package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Optional;

import static jp.cafebabe.clpond.util.AssertHelper.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataSourceFactoriesTest {
    private final DataSourceFactories factories = new DataSourceFactories();

    @Test
    public void testDirectory() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("src/test/resources"), FileSystems.getDefault());

        assertTrue(factory.isPresent());
        assertInstanceOf(DirectoryDataSourceFactory.class, factory.get());
    }

    @Test
    public void testJar() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar"));

        assertTrue(factory.isPresent());
        assertInstanceOf(JarFileDataSourceFactory.class, factory.get());
    }

    @Test
    public void testNotExistsJar() {
        assertThrows(IOException.class, 
                () -> factories.find(Paths.get("not/exists/jar")));
    }

}
