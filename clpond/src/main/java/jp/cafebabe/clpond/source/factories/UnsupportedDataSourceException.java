package jp.cafebabe.clpond.source.factories;

import java.io.IOException;
import java.io.Serial;

public class UnsupportedDataSourceException extends IOException {
    @Serial
    private static final long serialVersionUID = -3466954463874687402L;

    public UnsupportedDataSourceException(String message){
        super(message);
    }

}
