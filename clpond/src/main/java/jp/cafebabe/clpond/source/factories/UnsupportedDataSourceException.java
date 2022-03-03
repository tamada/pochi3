package jp.cafebabe.clpond.source.factories;

import java.io.IOException;

public class UnsupportedDataSourceException extends IOException {
    private static final long serialVersionUID = -3466954463874687402L;

    public UnsupportedDataSourceException(String message){
        super(message);
    }

}
