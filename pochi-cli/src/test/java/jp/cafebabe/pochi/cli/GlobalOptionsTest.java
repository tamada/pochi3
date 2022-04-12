package jp.cafebabe.pochi.cli;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GlobalOptionsTest {
    @Test
    public void testConfig() throws Exception {
        GlobalOptions opts = new GlobalOptions();
        var config = opts.config();
        assertNotNull(config);
        assertEquals(0L, config.properties().count());
        assertEquals(7L, config.rules().count());
    }
    @Test
    public void testConfigPath() {
        GlobalOptions opts = new GlobalOptions();
        assertEquals(Optional.empty(), opts.configPath());
    }

    @Test
    public void testPochiHome() {
        GlobalOptions opts = new GlobalOptions();
        assertEquals(Path.of("/opt/homebrew/opt/pochi"), opts.pochiHome());
    }

    @Test
    public void testPochiHomeBySysProperty() {
        execWithSysProp("pochi.home", "../birthmarks/src/main/resources/resources", () -> {
            GlobalOptions opts = new GlobalOptions();
            assertEquals(Path.of("../birthmarks/src/main/resources/resources"), opts.pochiHome());
        });
    }

    public void execWithSysProp(String key, String value, Runnable runnable) {
        System.setProperty(key, value);
        runnable.run();
        System.clearProperty(key);

    }
}
