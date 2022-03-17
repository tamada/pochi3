package jp.cafebabe.birthmarks.extractors;

import io.vavr.control.Either;
import jp.cafebabe.clpond.source.DataSource;

public interface Extractor {
    Either<Throwable, Birthmarks> extract(DataSource source);
}
