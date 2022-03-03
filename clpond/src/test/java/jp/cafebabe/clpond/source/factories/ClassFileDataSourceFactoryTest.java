package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassFileDataSourceFactoryTest {
    private Path path;
    private Path dummyPath;
    private ClassFileDataSourceFactory factory = new ClassFileDataSourceFactory();

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/classes/sample/hello/HelloWorld.class");
        dummyPath = Paths.get("target/test-classes/dummy.class");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(path), is(true));
        assertThat(factory.isTarget(dummyPath), is(false));

        DataSource source = factory.build(new File(path.toString()));
        Entry[] entries = source.stream().toArray(size -> new Entry[size]);

        assertThat(entries.length, is(1));
        assertThat(entries[0].className(), is(new ClassName("sample.hello.HelloWorld")));
        assertThat(entries[0].isClass(), is(true));
    }
}
