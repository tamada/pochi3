package jp.cafebabe.pochi.comparators.algorithms;

public record ScoreParameter(int match, int mismatch, int gap) {
    public static ScoreParameter of(int match, int mismatch, int gap) {
        return new ScoreParameter(match, mismatch, gap);
    }
}
