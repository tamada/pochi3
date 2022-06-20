package jp.cafebabe.birthmarks.config;

import jp.cafebabe.birthmarks.io.ResourceFinder;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationParserTest {
    @Test
    public void testParse() {
        var either = ResourceFinder.parse("/test-resources/config1.json",
                new ConfigurationParser());

        assertTrue(either.isRight());
        Configuration config = either.get();

        assertEquals("value1", config.value("key1"));
        assertEquals("value2", config.value("key2"));
        assertEquals("not_exists", config.value("key3", "not_exists"));

        assertTrue(config.match("java.lang.String"));
        assertFalse(config.match("javax.swing.JFrame"));
        assertTrue(config.match("SampleTest"));
        assertTrue(config.match("SampleImpl"));
        assertTrue(config.match("Ignore"));
    }

    @Test
    public void testParseWithProperties() throws Exception {
        var optional = ResourceFinder.find("/test-resources/config1.json");
        var parser = new ConfigurationParser();

        assertTrue(optional.isPresent());
        var url = optional.get();
        var config = parser.parse(url.openStream(), Map.of("key3", "value3", "key4", "value4", "key2", "newValue2"));

        assertEquals("value1", config.value("key1"));
        assertEquals("newValue2", config.value("key2"));
        assertEquals("value3", config.value("key3"));
        assertEquals("value4", config.value("key4"));

        assertTrue(config.match("java.lang.String"));
        assertFalse(config.match("javax.swing.JFrame"));
        assertTrue(config.match("SampleTest"));
        assertTrue(config.match("SampleImpl"));
        assertTrue(config.match("Ignore"));
    }
}
