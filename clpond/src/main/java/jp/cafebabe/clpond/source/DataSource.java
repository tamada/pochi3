package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This interface shows DataSource, which is the source of some data.
 * 
 * @author Haruaki Tamada
 */
public interface DataSource extends AutoCloseable {
    Path base();

    Stream<Entry> stream();

    default DataSource filter(Predicate<Entry> predicate) {
        return new FilteredDataSource(this, predicate);
    }

    default void forEach(Consumer<Entry> consumer){
        stream().forEach(consumer);
    }

    @Override
    void close() throws IOException;
}
