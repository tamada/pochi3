package jp.cafebabe.clpond.sink;

import jp.cafebabe.clpond.entities.Entry;

import java.io.IOException;

public abstract class AbstractDataSink implements DataSink {
    /**
     * Close this instance.
     * @throws IOException I/O error
     */
    @Override
    public void close() throws IOException {
    }

    String toJVMClassName(Entry entry){
        if(!entry.isClass())
            throw new IllegalArgumentException();
        return convertToJVMClassName(entry);
    }

    private String convertToJVMClassName(Entry entry){
        return entry.className()
            .toString()
            .replace('.', '/');
    }
}
