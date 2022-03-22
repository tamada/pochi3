package jp.cafebabe.birthmarks.comparators;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.*;

public record Pair<L, R>(L left, R right) implements Serializable {
    private static final long serialVersionUID = 8512733686835549454L;

    public Pair(L left, R right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public Pair<R, L> swap() {
        return of(right, left);
    }

    public void accept(BiConsumer<L, R> action) {
        action.accept(left, right);
    }

    public void accept(Consumer<L> leftAction, Consumer<R> rightAction) {
        leftAction.accept(left);
        rightAction.accept(right);
    }

    public void ifAndElse(BiPredicate<L, R> predicate, BiConsumer<L, R> trueAction) {
        ifAndElse(predicate, trueAction, (l, r) -> {});
    }

    public void ifAndElse(BiPredicate<L, R> predicate, BiConsumer<L, R> trueAction, BiConsumer<L, R> falseAction) {
        if(predicate.test(left, right))
            trueAction.accept(left, right);
        else
            falseAction.accept(left, right);
    }

    public <V> V ifAndElse(Predicate<Pair<L, R>> predicate, V trueValue, V falseValue) {
        if(predicate.test(this))
            return trueValue;
        return falseValue;
    }

    public <V> V ifAndElse(BiPredicate<L, R> predicate, V trueValue, V falseValue) {
        if(predicate.test(left, right))
            return trueValue;
        return falseValue;
    }

    public <K> K unify(BiFunction<L, R, K> mapper) {
        return mapper.apply(left, right);
    }

    public <LL, RR> Pair<LL, RR> map(Function<L, LL> leftMapper, Function<R, RR> rightMapper) {
        return of(leftMapper.apply(left),
                rightMapper.apply(right));
    }

    public boolean testAnd(Predicate<L> leftPredicate, Predicate<R> rightPredicate) {
        return leftPredicate.test(left) && rightPredicate.test(right);
    }

    public boolean testOr(Predicate<L> leftPredicate, Predicate<R> rightPredicate) {
        return leftPredicate.test(left) || rightPredicate.test(right);
    }

    public boolean test(BiPredicate<L, R> predicate) {
        return predicate.test(left, right);
    }

    public Pair<Boolean, Boolean> test(Predicate<L> leftPredicate, Predicate<R> rightPredicate) {
        return map(l -> leftPredicate.test(l),
                r -> rightPredicate.test(r));
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", left, right);
    }
}