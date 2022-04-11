package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PlainFileDataSourceBuilderTest {
    private Path path;
    private Path dummyPath;
    private final DataSourceBuilder factory = new PlainFileDataSourceBuilder();

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/classes/sample/hello/HelloWorld.class");
        dummyPath = Paths.get("src/test/resources/dummy.class");
    }

    @Test
    public void testBasic() throws Exception{
        assertTrue(factory.isTarget(path));
        assertFalse(factory.isTarget(dummyPath));

        DataSource source = factory.build(new File(path.toString()));
        Entry[] entries = source.stream().toArray(Entry[]::new);

        assertEquals(1, entries.length);
        assertTrue(entries[0].endsWith(".class"));
        assertEquals(path.toUri(), entries[0].loadFrom());
        assertNull(entries[0].className());
        assertTrue(entries[0].isClass());
        assertEquals(String.format("%s <%s>", path.toUri(), path), entries[0].toString());
    }
}
