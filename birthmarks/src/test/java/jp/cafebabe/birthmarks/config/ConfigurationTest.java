package jp.cafebabe.birthmarks.config;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationTest {
    @Test
    public void testDefault() {
        var config = Configuration.defaultConfig();
        assertEquals(0L, config.properties().count());
        assertEquals(7, config.rules().count());
    }

    @Test
    public void testToJsonEmptyConfig() {
        var config = new Configuration(new Rules(), Map.of());
        assertEquals("{\"properties\":{},\"rules\":[]}", config.toJson());
    }
}
