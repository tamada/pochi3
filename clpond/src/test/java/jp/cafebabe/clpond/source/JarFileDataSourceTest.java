package jp.cafebabe.clpond.source;


import jp.cafebabe.clpond.entities.ClassName;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.factories.DataSourceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JarFileDataSourceTest {
    private Path path;

    @BeforeEach
    public void setUp(){
        path = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = DataSourceFactory.instance();

        assertThat(factory.isTarget(path), is(true));

        DataSource source = factory.build(path);

        Entry[] entries = source.stream()
                .sorted(new EntryComparator())
                .toArray(count -> new Entry[count]);
        assertThat(entries.length, is(5));

        assertThat(entries[0].isName("META-INF/MANIFEST.MF"), is(true));
        assertThat(entries[0].isClass(), is(false));

        assertThat(entries[1].isName("META-INF/maven/sample/hello/pom.properties"), is(true));
        assertThat(entries[1].isClass(), is(false));

        assertThat(entries[2].isName("META-INF/maven/sample/hello/pom.xml"), is(true));
        assertThat(entries[2].isClass(), is(false));

        assertThat(entries[3].isName("sample/hello/HelloWorld.class"), is(true));
        assertThat(entries[3].isClass(), is(true));
        assertThat(entries[3].className(), is(new ClassName("sample.hello.HelloWorld")));

        assertThat(entries[4].isName("sample/hello/Launcher.class"), is(true));
        assertThat(entries[4].isClass(), is(true));
        assertThat(entries[4].className(), is(new ClassName("sample.hello.Launcher")));
    }
}
