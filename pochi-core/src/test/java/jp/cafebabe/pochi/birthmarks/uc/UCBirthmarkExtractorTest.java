package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.ConfigurationParser;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.io.ResourceFinder;
import jp.cafebabe.clpond.entities.Name;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.factories.DataSourceFactory;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UCBirthmarkExtractorTest {
    public Birthmarks extract(String path) throws Exception{
        URL location = getClass().getResource(path);
        var config = ResourceFinder.parse("/resources/config.json", new ConfigurationParser()).get();
        DataSource source = DataSourceFactory.instance().build(Paths.get(location.toURI()));
        Extractor extractor = new UsedClassesExtractorBuilder().build(config);
        return extractor.extract(source);
    }

    @Test
    public void testBasic() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/HelloWorld.class");

        assertTrue(birthmarks.find(Name.of("HelloWorld")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().toList();

        assertEquals(7, elements.size());
        assertEquals("java.io.PrintStream", elements.get(0).value());
        assertEquals("java.io.PrintStream", elements.get(1).value());
        assertEquals("java.lang.Object", elements.get(2).value());
        assertEquals("java.lang.Object", elements.get(3).value());
        assertEquals("java.lang.String", elements.get(4).value());
        assertEquals("java.lang.String", elements.get(5).value());
        assertEquals("java.lang.System", elements.get(6).value());
    }

    @Test
    public void testBasic2() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/Fibonacci.class");

        assertTrue(birthmarks.find(Name.of("Fibonacci")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().distinct().sorted().toList();

        assertEquals(13, elements.size());
        assertEquals("java.io.PrintStream", elements.get(0).value());
        assertEquals("java.lang.Integer", elements.get(1).value());
        assertEquals("java.lang.Object", elements.get(2).value());
        assertEquals("java.lang.String", elements.get(3).value());
        assertEquals("java.lang.System", elements.get(4).value());
        assertEquals("java.util.Iterator", elements.get(5).value());
        assertEquals("java.util.List", elements.get(6).value());
        assertEquals("java.util.function.IntFunction", elements.get(7).value());
        assertEquals("java.util.function.IntUnaryOperator", elements.get(8).value());
        assertEquals("java.util.stream.Collector", elements.get(9).value());
        assertEquals("java.util.stream.Collectors", elements.get(10).value());
        assertEquals("java.util.stream.IntStream", elements.get(11).value());
        assertEquals("java.util.stream.Stream", elements.get(12).value());
    }

    @Test
    public void testBasic3() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/MazeBuilder.class");

        assertTrue(birthmarks.find(Name.of("MazeBuilder")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().distinct().sorted().toList();

        assertEquals(6, elements.size());
        assertEquals("java.io.PrintStream", elements.get(0).value());
        assertEquals("java.lang.Integer", elements.get(1).value());
        assertEquals("java.lang.Object", elements.get(2).value());
        assertEquals("java.lang.String", elements.get(3).value());
        assertEquals("java.lang.System", elements.get(4).value());
        assertEquals("java.util.Random", elements.get(5).value());
    }

    @Test
    public void testBasic4() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/MyServer2.class");

        assertTrue(birthmarks.find(Name.of("MyServer2")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().toList();

        assertEquals(10, elements.size());
        assertEquals("java.io.IOException", elements.get(0).value());
        assertEquals("java.lang.Object", elements.get(1).value());
        assertEquals("java.lang.Object", elements.get(2).value());
        assertEquals("java.net.ServerSocket", elements.get(3).value());
        assertEquals("java.net.ServerSocket", elements.get(4).value());
        assertEquals("java.net.ServerSocket", elements.get(5).value());
        assertEquals("java.net.ServerSocket", elements.get(6).value());
        assertEquals("java.net.ServerSocket", elements.get(7).value());
        assertEquals("java.net.ServerSocket", elements.get(8).value());
        assertEquals("java.net.ServerSocket", elements.get(9).value());
    }
}
