package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.factories.DataSourceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassFileDataSourceTest {
    private Path path;

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/classes/sample/hello/HelloWorld.class");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = DataSourceFactory.instance();

        assertTrue(factory.isTarget(path));

        try(DataSource source = factory.build(path)){
            Entry[] entries = source.stream().toArray(Entry[]::new);
            assertEquals(1, entries.length);
            assertTrue(entries[0].isName("sample/hello/HelloWorld.class"));
            assertTrue(entries[0].isClass());
            assertEquals(new ClassName("sample.hello.HelloWorld"), entries[0].className());
        }
    }
}
