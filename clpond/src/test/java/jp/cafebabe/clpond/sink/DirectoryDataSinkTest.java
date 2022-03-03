package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.factories.DataSourceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectoryDataSinkTest {
    @BeforeEach
    public void setUp() throws Exception{
        Path path = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar");
        try(DataSource source = DataSourceFactory.instance().build(path);
            DataSink sink = new DirectoryDataSink(Paths.get("dir"))){
                sink.consume(source);
        }
    }

    @Test
    public void testCreatedDirectory() throws Exception{
        DataSource source = DataSourceFactory.instance().build(Paths.get("dir"));

        List<Entry> list = new ArrayList<>();
        source.forEach(entry -> list.add(entry));

        assertThat(list.size(), is(2));
    }

    @AfterEach
    public void tearDown() throws Exception{
        PathHelper.deleteAll(Paths.get("dir"));
    }
}
