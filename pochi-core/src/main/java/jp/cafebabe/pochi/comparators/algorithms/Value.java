package jp.cafebabe.pochi.comparators.algorithms;

public record Value(int value, Index2D index) {
    public Value update(int newValue, Index2D index) {
        if(this.value() < newValue)
            return new Value(newValue, index);
        return this;
    }
}
