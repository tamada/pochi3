package jp.cafebabe.clpond.source.factories;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultDataSourceFactoryTest {
    private final DataSourceFactory factory = new DefaultDataSourceFactory();
    private final Path path = Paths.get("src/test/resources/hello/target/classes");

    @Test
    public void testBasic() throws Exception{
        assertTrue(factory.isTarget(path));
        assertFalse(factory.isTarget(null, null, null));
    }

    @Test
    public void testBasic2() {
        assertFalse(factory.isTarget(null));
    }
}
