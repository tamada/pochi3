package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Optional;

import static jp.cafebabe.clpond.util.AssertHelper.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataSourceBuilderFactoryTest {
    private final DataSourceBuilderFactory factories = new DataSourceBuilderFactory();

    @Test
    public void testDirectory() throws Exception{
        Optional<DataSourceBuilder> factory = factories.find(Paths.get("src/test/resources"), FileSystems.getDefault());

        assertTrue(factory.isPresent());
        assertInstanceOf(DirectoryDataSourceBuilder.class, factory.get());
    }

    @Test
    public void testJar() throws Exception{
        Optional<DataSourceBuilder> factory = factories.find(Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar"));

        assertTrue(factory.isPresent());
        assertInstanceOf(JarFileDataSourceBuilder.class, factory.get());
    }

    @Test
    public void testNotExistsJar() {
        assertThrows(IOException.class, 
                () -> factories.find(Paths.get("not/exists/jar")));
    }

}
