package jp.cafebabe.clpond.sink;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.Entry;
import jp.cafebabe.clpond.source.DataSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This abstract class is the super class for sinking entries.
 *
 * @author Haruaki TAMADA
 */
public interface DataSink extends AutoCloseable{
    /**
     * write given data to this instance.
     * @param data  writing data
     * @param entry meta data of data
     * @throws IOException I/O error
     */
    default void consume(byte[] data, Entry entry) throws IOException {
        consume(new ByteArrayInputStream(data), entry);
    }

    /**
     * write data from the given entry to this instance.
     *
     * @param entry writing data and its meta data
     * @throws IOException I/O error
     */
    default void consume(Entry entry) throws IOException {
        consume(entry.openStream(), entry);
    }

    /**
     * write given data from stream to this instance.
     * @param in writing data
     * @param entry meta data of data
     * @throws IOException I/O error
     */
    void consume(InputStream in, Entry entry) throws IOException;

    /**
     * write given data entries to this instance.
     * @param source writing data
     */
    default void consume(DataSource source){
        source.stream().forEach(entry ->
            Try.run(() -> consume(entry)));
    }

    /**
     * closes this instance.
     * After the calling this method, writing data of this instance causes the I/O error.
     * @throws IOException I/O error
     */
    @Override
    void close() throws IOException;
}
