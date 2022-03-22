import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;
import jp.cafebabe.pochi.comparators.*;
import jp.cafebabe.pochi.pairers.GuessedPairer;
import jp.cafebabe.pochi.pairers.RoundRobinPairer;
import jp.cafebabe.pochi.pairers.SpecifiedPairer;

module jp.cafebabe.pochi.core {
    requires io.vavr;
    requires org.objectweb.asm;
    requires jp.cafebabe.pochi.clpond;
    requires jp.cafebabe.pochi.birthmarks;

    provides jp.cafebabe.birthmarks.extractors.ExtractorBuilder with
            KGramBasedExtractorBuilder,
            UsedClassesExtractorBuilder;

    provides jp.cafebabe.birthmarks.comparators.ComparatorBuilder with
            CosineComparator.Builder,
            DiceIndexComparator.Builder,
            EditDistanceComparator.Builder,
            JaccardIndexComparator.Builder,
            LongestCommonSubsequenceComparator.Builder,
            SimpsonIndexComparator.Builder;

    provides jp.cafebabe.birthmarks.pairers.PairerBuilder with
            GuessedPairer.Builder,
            RoundRobinPairer.Builder,
            RoundRobinPairer.WithSamePairBuilder,
            SpecifiedPairer.Builder;

    opens jp.cafebabe.pochi.comparators;
    opens jp.cafebabe.pochi.pairers;
    opens jp.cafebabe.pochi.pairers.relationers;
}