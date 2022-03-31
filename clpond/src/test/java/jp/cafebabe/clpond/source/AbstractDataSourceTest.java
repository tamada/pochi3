package jp.cafebabe.clpond.source;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractDataSourceTest{
    @Test
    public void testBasic() throws Exception{
        try(PlainFileDataSource source = new PlainFileDataSource(Paths.get("target/test-classes/dummy.class/emptyfile"))){
            assertEquals(1, source.getStartIndex("/hoge.class"));
            assertEquals(0, source.getStartIndex("hoge.class"));
            assertEquals("hoge", source.trimName("/hoge.class", 1, 5));
            assertEquals("aaa", source.trimName("aaa", -1, 1));
            assertEquals("aaa", source.trimName("aaa", 2, 1));
            assertEquals("a", source.trimName("aaa", 0, 1));
        }
    }

}
