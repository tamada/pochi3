package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JarFileDataSinkTest {
    @Test
    public void testCreatedJarFile() throws Exception{
        Path path = Paths.get("src/test/resources/hello/target/classes/");
        try(DataSource source = DataSourceBuilder.instance().build(path);
            DataSink sink = new JarFileDataSink(Paths.get("hoge.jar"))){
                sink.consume(source);
        }

        DataSource source = DataSourceBuilder.instance().build(Paths.get("hoge.jar"));

        List<Entry> list = new ArrayList<>();
        source.forEach(list::add);

        assertEquals(2, list.size());
    }

    @Test
    public void testJarFromJar() throws Exception{
        Path path = Paths.get("src/test/resources/hello/target/hello-1.0-SNAPSHOT.jar");
        try(DataSource source = DataSourceBuilder.instance().build(path);
            DataSink sink = new JarFileDataSink(Paths.get("hoge.jar"))){
                sink.consume(source);
        }

        DataSource source = DataSourceBuilder.instance().build(Paths.get("hoge.jar"));

        List<Entry> list = new ArrayList<>();
        source.forEach(list::add);

        assertEquals(5, list.size());
    }

    @AfterEach
    public void tearDown() throws Exception{
        Files.deleteIfExists(Paths.get("hoge.jar"));
    }
}
