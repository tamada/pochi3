package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.comparators.Comparison;
import jp.cafebabe.birthmarks.comparators.Comparisons;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.io.BirthmarkJsonier;
import jp.cafebabe.birthmarks.io.BirthmarksJsonier;
import jp.cafebabe.birthmarks.io.ComparisonJsonier;
import jp.cafebabe.birthmarks.io.ComparisonsJsonier;

public class Jsonier {
    private BirthmarkJsonier birthmark = new BirthmarkJsonier();
    private BirthmarksJsonier birthmarks = new BirthmarksJsonier();
    private ComparisonJsonier comparison = new ComparisonJsonier();
    private ComparisonsJsonier comparisons = new ComparisonsJsonier();

    public String toJson(Birthmark target) {
        return birthmark.toJson(target);
    }

    public String toJson(Birthmarks target) {
        return birthmarks.toJson(target);
    }

    public String toJson(Comparison target) {
        return comparison.toJson(target);
    }

    public String toJson(Comparisons target) {
        return comparisons.toJson(target);
    }
}
