package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.config.Configuration;

public interface ComparatorBuilder {
    ComparatorType type();

    Comparator build(Configuration config);
}
