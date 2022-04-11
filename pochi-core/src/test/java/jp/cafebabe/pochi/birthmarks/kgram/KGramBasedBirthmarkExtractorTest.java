package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.ConfigurationParser;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.io.ResourceFinder;
import jp.cafebabe.clpond.entities.Name;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.factories.DataSourceBuilder;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KGramBasedBirthmarkExtractorTest {
    public Birthmarks extract(String path, String type) throws Exception{
        URL location = getClass().getResource(path);
        var config = ResourceFinder.parse("/resources/config.json", new ConfigurationParser()).get();
        ExtractorBuilder builder = new ExtractorBuilderFactory().builder(type).get();
        DataSource source = DataSourceBuilder.instance().build(Paths.get(location.toURI()));
        Extractor extractor = builder.build(config);
        return extractor.extract(source);
    }

    @Test
    public void testBasic() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/HelloWorld.class", "2-gram");

        assertTrue(birthmarks.find(Name.of("HelloWorld")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().toList();

        assertEquals(5, elements.size());
        assertEquals("25,183",  elements.get(0).value());  // aload(19) invokespecial(b7)
        assertEquals("183,177", elements.get(1).value());  // invokespecial(b7) return(b1)
        assertEquals("178,18",  elements.get(2).value());  // getstatic(b2) ldc(12)
        assertEquals("18,182",  elements.get(3).value());  // ldc(12) invokevirtual(b6)
        assertEquals("182,177", elements.get(4).value());  // invokevirtual(b6) return(b1)
    }

    @Test
    public void testBasic2() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/Fibonacci.class", "3-gram");

        assertTrue(birthmarks.find(Name.of("Fibonacci")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());

        var elements = list.get(0).stream().collect(Collectors.toList());
        assertEquals(40, elements.size());

        // Constructor
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKESPECIAL, Opcodes.RETURN), elements.get(0).value());

        // run method
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.BIPUSH, Opcodes.INVOKEVIRTUAL), elements.get(1).value());
        assertEquals(toKGram(Opcodes.BIPUSH, Opcodes.INVOKEVIRTUAL, Opcodes.ASTORE), elements.get(2).value());
        assertEquals(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.ASTORE, Opcodes.ALOAD), elements.get(3).value());
        assertEquals(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE), elements.get(4).value());
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.ASTORE), elements.get(5).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.ASTORE, Opcodes.ALOAD), elements.get(6).value());
        assertEquals(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE), elements.get(7).value());
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.IFEQ), elements.get(8).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.IFEQ, Opcodes.ALOAD), elements.get(9).value());
        assertEquals(toKGram(Opcodes.IFEQ, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE), elements.get(10).value());
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST), elements.get(11).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST, Opcodes.ASTORE), elements.get(12).value());
        assertEquals(toKGram(Opcodes.CHECKCAST, Opcodes.ASTORE, Opcodes.GETSTATIC), elements.get(13).value());
        assertEquals(toKGram(Opcodes.ASTORE, Opcodes.GETSTATIC, Opcodes.ALOAD), elements.get(14).value());
        assertEquals(toKGram(Opcodes.GETSTATIC, Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL), elements.get(15).value());
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.GOTO), elements.get(16).value());
        assertEquals(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.GOTO, Opcodes.RETURN), elements.get(17).value());

        // stream method
        assertEquals(toKGram(Opcodes.ICONST_1, Opcodes.NEW, Opcodes.DUP), elements.get(18).value());
        assertEquals(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.ACONST_NULL), elements.get(19).value());
        assertEquals(toKGram(Opcodes.DUP, Opcodes.ACONST_NULL, Opcodes.INVOKESPECIAL), elements.get(20).value());
        assertEquals(toKGram(Opcodes.ACONST_NULL, Opcodes.INVOKESPECIAL, Opcodes.INVOKESTATIC), elements.get(21).value());
        assertEquals(toKGram(Opcodes.INVOKESPECIAL, Opcodes.INVOKESTATIC, Opcodes.ARETURN), elements.get(22).value());

        // fibonacci method
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.ILOAD), elements.get(23).value());
        assertEquals(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.ILOAD, Opcodes.I2L), elements.get(24).value());
        assertEquals(toKGram(Opcodes.ILOAD, Opcodes.I2L, Opcodes.INVOKEINTERFACE), elements.get(25).value());
        assertEquals(toKGram(Opcodes.I2L, Opcodes.INVOKEINTERFACE, Opcodes.INVOKEDYNAMIC), elements.get(26).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.INVOKEDYNAMIC, Opcodes.INVOKEINTERFACE), elements.get(27).value());
        assertEquals(toKGram(Opcodes.INVOKEDYNAMIC, Opcodes.INVOKEINTERFACE, Opcodes.INVOKESTATIC), elements.get(28).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.INVOKESTATIC, Opcodes.INVOKEINTERFACE), elements.get(29).value());
        assertEquals(toKGram(Opcodes.INVOKESTATIC, Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST), elements.get(30).value());
        assertEquals(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST, Opcodes.ARETURN), elements.get(31).value());

        // main method
        assertEquals(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.INVOKESPECIAL), elements.get(32).value());
        assertEquals(toKGram(Opcodes.DUP, Opcodes.INVOKESPECIAL, Opcodes.ASTORE), elements.get(33).value());
        assertEquals(toKGram(Opcodes.INVOKESPECIAL, Opcodes.ASTORE, Opcodes.ALOAD), elements.get(34).value());
        assertEquals(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL), elements.get(35).value());
        assertEquals(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.RETURN), elements.get(36).value());

        // inner method
        assertEquals(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.ILOAD), elements.get(37).value());
        assertEquals(toKGram(Opcodes.DUP, Opcodes.ILOAD, Opcodes.INVOKESPECIAL), elements.get(38).value());
        assertEquals(toKGram(Opcodes.ILOAD, Opcodes.INVOKESPECIAL, Opcodes.ARETURN), elements.get(39).value());
    }

    private String toKGram(int... values){
        return KGramBuilder.from(values).toString();
    }

    @Test
    public void testBasic3() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/MazeBuilder.class", "4-gram");

        assertTrue(birthmarks.find(Name.of("MazeBuilder")).isPresent());

        List<Birthmark> list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());
    }

    @Test
    public void testBasic4() throws Exception{
        Birthmarks birthmarks = extract("/test-resources/MyServer2.class", "5-gram");

        assertTrue(birthmarks.find(Name.of("MyServer2")).isPresent());

        var list = birthmarks.stream().collect(Collectors.toList());
        assertEquals(1, list.size());
    }
}
