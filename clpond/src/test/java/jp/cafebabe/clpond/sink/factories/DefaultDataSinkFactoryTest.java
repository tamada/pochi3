package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.ClassFileDataSink;
import jp.cafebabe.clpond.sink.DirectoryDataSink;
import jp.cafebabe.clpond.sink.JarFileDataSink;
import jp.cafebabe.clpond.sink.WarFileDataSink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DefaultDataSinkFactoryTest {
    private DataSinkFactory factory = new DefaultDataSinkFactory();

    @Test
    public void testBasic(){
        assertThat(factory.isTarget(Paths.get("hoge.jar")), is(true));
        assertThat(factory.isTarget(Paths.get("hoge.war")), is(true));
        assertThat(factory.isTarget(Paths.get("hoge.class")), is(true));
        assertThat(factory.isTarget(Paths.get("dir")), is(true));
        assertThat(factory.isTarget(null), is(false));
    }

    @Test
    public void testBuiltDataSink() throws Exception{
        assertThat(factory.create(Paths.get("hoge.jar")),   is(instanceOf(JarFileDataSink.class)));
        assertThat(factory.create(Paths.get("hoge.war")),   is(instanceOf(WarFileDataSink.class)));
        assertThat(factory.create(Paths.get("hoge.class")), is(instanceOf(ClassFileDataSink.class)));
        assertThat(factory.create(Paths.get("dir")),        is(instanceOf(DirectoryDataSink.class)));
    }

    @AfterEach
    public void tearDown() throws Exception{
        Files.deleteIfExists(Paths.get("hoge.jar"));
        Files.deleteIfExists(Paths.get("hoge.war"));
        Files.deleteIfExists(Paths.get("hoge.class"));
        PathHelper.deleteAll(Paths.get("dir"));
    }
}
