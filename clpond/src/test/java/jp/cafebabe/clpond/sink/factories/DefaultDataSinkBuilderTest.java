package jp.cafebabe.clpond.sink.factories;

import jp.cafebabe.clpond.entities.PathHelper;
import jp.cafebabe.clpond.sink.ClassFileDataSink;
import jp.cafebabe.clpond.sink.DirectoryDataSink;
import jp.cafebabe.clpond.sink.JarFileDataSink;
import jp.cafebabe.clpond.sink.WarFileDataSink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDataSinkBuilderTest {
    private final DataSinkBuilder factory = new DefaultDataSinkBuilder();

    @Test
    public void testBasic(){
        assertTrue(factory.isTarget(Paths.get("hoge.jar")));
        assertTrue(factory.isTarget(Paths.get("hoge.war")));
        assertTrue(factory.isTarget(Paths.get("hoge.class")));
        assertTrue(factory.isTarget(Paths.get("dir")));
        assertFalse(factory.isTarget(null));
    }

    @Test
    public void testBuiltDataSink() {
        assertInstanceOf(JarFileDataSink.class, factory.build(Paths.get("hoge.jar")));
        assertInstanceOf(WarFileDataSink.class, factory.build(Paths.get("hoge.war")));
        assertInstanceOf(ClassFileDataSink.class, factory.build(Paths.get("hoge.class")));
        assertInstanceOf(DirectoryDataSink.class, factory.build(Paths.get("dir")));
    }

    @AfterEach
    public void tearDown() throws Exception{
        Files.deleteIfExists(Paths.get("hoge.jar"));
        Files.deleteIfExists(Paths.get("hoge.war"));
        Files.deleteIfExists(Paths.get("hoge.class"));
        PathHelper.deleteAll(Paths.get("dir"));
    }
}
