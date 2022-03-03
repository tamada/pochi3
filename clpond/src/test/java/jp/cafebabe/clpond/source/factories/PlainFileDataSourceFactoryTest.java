package jp.cafebabe.clpond.source.factories;

import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlainFileDataSourceFactoryTest {
    private Path path;
    private Path dummyPath;
    private DataSourceFactory factory = new PlainFileDataSourceFactory();

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/classes/sample/hello/HelloWorld.class");
        dummyPath = Paths.get("src/test/resources/dummy.class");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(path), is(true));
        assertThat(factory.isTarget(dummyPath), is(false));

        DataSource source = factory.build(new File(path.toString()));
        Entry[] entries = source.stream().toArray(size -> new Entry[size]);

        assertThat(entries.length, is(1));
        assertThat(entries[0].endsWith(".class"), is(true));
        assertThat(entries[0].loadFrom(), is(path.toUri()));
        assertThat(entries[0].className(), is(nullValue()));
        assertThat(entries[0].isClass(), is(true));
        assertThat(entries[0].toString(), is(String.format("%s <%s>", path.toUri(), path)));
    }
}
