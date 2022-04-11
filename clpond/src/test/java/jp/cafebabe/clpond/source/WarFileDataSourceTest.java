package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class WarFileDataSourceTest {
    private Path path;

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.war");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceBuilder factory = DataSourceBuilder.instance();

        assertTrue(factory.isTarget(path));

        DataSource source = factory.build(path);

        Entry[] entries = source.stream()
                .sorted(new EntryComparator())
                .toArray(Entry[]::new);
        assertEquals(7, entries.length);

        assertTrue(entries[0].isName("META-INF/MANIFEST.MF"));
        assertFalse(entries[0].isClass());

        assertTrue(entries[1].isName("META-INF/maven/sample/hello/pom.properties"));
        assertFalse(entries[1].isClass());

        assertTrue(entries[2].isName("META-INF/maven/sample/hello/pom.xml"));
        assertFalse(entries[2].isClass());

        assertTrue(entries[3].isName("WEB-INF/classes/sample/hello/HelloWorld.class"));
        assertTrue(entries[3].isClass());
        assertEquals(new ClassName("sample.hello.HelloWorld"), entries[3].className());

        assertTrue(entries[4].isName("WEB-INF/classes/sample/hello/Launcher.class"));
        assertTrue(entries[4].isClass());
        assertEquals(new ClassName("sample.hello.Launcher"), entries[4].className());

        assertTrue(entries[5].isName("WEB-INF/web.xml"));
        assertFalse(entries[5].isClass());

        assertTrue(entries[6].isName("index.html"));
        assertFalse(entries[6].isClass());
    }
}
