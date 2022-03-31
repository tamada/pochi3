package jp.cafebabe.clpond.util;

import io.vavr.control.Try;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertHelper {
    public static <E extends Exception> void assertThrows(Class<E> exceptionClass, ThrowableProcessor<Exception> processor){
        Try.run(processor::perform)
                .onFailure(throwable -> assertTrue(exceptionClass.isInstance(throwable)));
    }
}
