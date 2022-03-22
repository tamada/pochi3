package jp.cafebabe.birthmarks;

import java.io.Serializable;

public class BirthmarkException extends Exception implements Serializable {
    public BirthmarkException() {
        super();
    }

    public BirthmarkException(Throwable cause) {
        super(cause);
    }

    public BirthmarkException(String message) {
        super(message);
    }
}
