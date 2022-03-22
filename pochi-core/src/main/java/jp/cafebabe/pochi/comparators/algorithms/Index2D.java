package jp.cafebabe.pochi.comparators.algorithms;

import java.util.Optional;

record Index2D(int x, int y) {
    public Optional<Index2D> relativeOf(int deltaX, int deltaY) {
        if((x + deltaX) < 0 || (y + deltaY) < 0)
            return Optional.empty();
        return Optional.of(new Index2D(x + deltaX, y + deltaY));
    }

    public int compute(Size max) {
        return y * max.width() + x;
    }
}
