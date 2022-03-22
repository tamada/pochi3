package jp.cafebabe.birthmarks.io;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceFinderTest {
    @Test
    public void testBasic() {
        var url = ResourceFinder.find("/resources/config.json");
        assertTrue(url.isPresent());
    }
}
