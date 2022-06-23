package jp.cafebabe.pochi.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VersionProviderTest {
    @Test
    public void testVersion() {
        var provider = new VersionProvider();
        String[] versionArray = provider.getVersion();

        assertEquals(1, versionArray.length);
        assertTrue(versionArray[0].startsWith("3."));
    }
}
