package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.impl.Builder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.net.URI;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarshallerTest {

    @Nested
    public class StringElementsTest {
        Birthmark birthmark = new Builder().metadata("name1", URI.create("."), "type1")
                .list(Stream.of("e1", "e2", "e3", "e4", "e5", "e6"));

        @Test
        public void testMarshalStringElements() {
            StringWriter out = new StringWriter();
            var marshaller = BirthmarkMarshaller.of(out);
            marshaller.marshal(birthmark);
            String wontResult = """
                    {"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":["e1","e2","e3","e4","e5","e6"]}
                    """.trim();
            assertEquals(wontResult, out.toString());
        }
    }

    @Nested
    public class LongElementsTest {
        Birthmark birthmark = new Builder().metadata("name1", URI.create("."), "type1")
                .list(Stream.of(2, 3, 5, 7, 11, 13, 17, 19));

        @Test
        public void testMarshalStringElements() {
            StringWriter out = new StringWriter();
            var marshaller = BirthmarkMarshaller.of(out);
            marshaller.marshal(birthmark);
            String wontResult = """
                    {"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":[2,3,5,7,11,13,17,19]}
                    """.trim();
            assertEquals(wontResult, out.toString());
        }
    }

    @Nested
    public class DoubleElementsTest {
        Birthmark birthmark = new Builder().metadata("name1", URI.create("."), "type1")
                .list(Stream.of(1.1, 1.2, 1.3, 1.4));

        @Test
        public void testMarshalStringElements() {
            StringWriter out = new StringWriter();
            var marshaller = BirthmarkMarshaller.of(out);
            marshaller.marshal(birthmark);
            String wontResult = """
                    {"container":"list","metadata":{"name":"name1","location":".","type":"type1"},"elements":[1.1,1.2,1.3,1.4]}
                    """.trim();
            assertEquals(wontResult, out.toString());
        }
    }
}
