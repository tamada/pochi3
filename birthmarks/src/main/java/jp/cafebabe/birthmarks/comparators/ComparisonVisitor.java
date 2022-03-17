package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.extractors.Birthmark;

public interface ComparisonVisitor {
    void visit(Birthmark left, Birthmark right, Similarity similarity);
}
