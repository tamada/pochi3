import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;
import jp.cafebabe.pochi.comparators.*;
import jp.cafebabe.pochi.pairers.GuessedPairer;
import jp.cafebabe.pochi.pairers.RoundRobinPairer;
import jp.cafebabe.pochi.pairers.SpecifiedPairer;

module jp.cafebabe.pochi.core {
    requires io.vavr;
    requires com.google.gson;
    requires org.objectweb.asm;
    requires jp.cafebabe.pochi.clpond;
    requires jp.cafebabe.pochi.birthmarks;

    uses jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
    provides jp.cafebabe.birthmarks.comparators.ComparatorBuilder with
            ChebyshevDistanceComparator.Builder,
            CosineComparator.Builder,
            DiceIndexComparator.Builder,
            EditDistanceComparator.Builder,
            JaccardIndexComparator.Builder,
            LongestCommonSubsequenceComparator.Builder,
            MinkowskiDistanceComparator.EuclideanBuilder,
            MinkowskiDistanceComparator.ManhattanBuilder,
            SimpsonIndexComparator.Builder,
            SmithWatermanComparator.Builder;

    uses jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
    provides jp.cafebabe.birthmarks.extractors.ExtractorBuilder with
            KGramBasedExtractorBuilder,
            UsedClassesExtractorBuilder;

    uses jp.cafebabe.birthmarks.pairers.PairerBuilder;
    provides jp.cafebabe.birthmarks.pairers.PairerBuilder with
            GuessedPairer.FullyBuilder,
            GuessedPairer.SimpleBuilder,
            RoundRobinPairer.Builder,
            RoundRobinPairer.WithSamePairBuilder,
            SpecifiedPairer.FullyBuilder,
            SpecifiedPairer.SimpleBuilder;

    exports jp.cafebabe.pochi.birthmarks;
    exports jp.cafebabe.pochi.birthmarks.kgram;
    exports jp.cafebabe.pochi.birthmarks.uc;
    exports jp.cafebabe.pochi.comparators;
    exports jp.cafebabe.pochi.pairers;
    exports jp.cafebabe.pochi.pairers.relationers;
    exports jp.cafebabe.pochi.utils;
}