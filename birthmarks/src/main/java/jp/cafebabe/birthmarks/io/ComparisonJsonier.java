package jp.cafebabe.birthmarks.io;

import jp.cafebabe.birthmarks.comparators.Comparison;
import jp.cafebabe.birthmarks.comparators.ComparisonVisitor;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.io.StringWriter;
import java.io.Writer;

public class ComparisonJsonier implements ComparisonVisitor, Jsonier<Comparison> {
    private StringWriter out;

    public String toJson(Comparison c) {
        out = new StringWriter();
        c.accept(this);
        return out.toString();
    }

    @Override
    public void visit(Birthmark left, Birthmark right, Similarity similarity) {
        out.write(String.format("{\"left\":{\"name\":\"%s\",\"location\":\"%s\"},\"right\":{\"name\":\"%s\",\"location\":\"%s\"},\"value\":%f}",
                left.name().name(), left.location(), right.name().name(), right.location(), similarity.value()));
    }
}
