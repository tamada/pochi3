package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An implemented class of DataSource with Stream.
 * 
 * @author Haruaki Tamada
 */
class FilteredDataSource extends AbstractDataSource {
    private final DataSource source;
    private final Predicate<Entry> predicate;

    public FilteredDataSource(DataSource source, Predicate<Entry> predicate) {
        super(source.base());
        this.source = source;
        this.predicate = predicate;
    }
    
    public Stream<Entry> stream() {
        return source.stream()
            .filter(predicate);
    }

    public void close() throws IOException {
        source.close();
    }
}
