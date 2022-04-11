package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jp.cafebabe.clpond.util.AssertHelper.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JarFileDataSourceBuilderTest {
    private Path jarfile;
    private Path directory;
    private final JarFileDataSourceBuilder factory = new JarFileDataSourceBuilder();

    @BeforeEach
    public void setUp(){
        jarfile = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar");
        directory = Paths.get("src/test/resources/hello/target/classes");
    }

    @Test
    public void testBasic() throws Exception{
        assertTrue(factory.isTarget(jarfile));
        assertFalse(factory.isTarget(directory));

        factory.build(new File(jarfile.toString()));
    }

    @Test
    public void testThrows() {
        assertThrows(UnsupportedDataSourceException.class, 
                () -> factory.build(directory));
    }
}
